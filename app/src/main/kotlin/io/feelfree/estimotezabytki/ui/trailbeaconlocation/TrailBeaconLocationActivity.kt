package io.feelfree.estimotezabytki.ui.trailbeaconlocation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat.*
import android.view.View
import com.estimote.proximity_sdk.monitoring.EstimoteMonitor
import com.estimote.proximity_sdk.monitoring.MonitorFactory
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.base.BaseActivity
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.feelfree.estimotezabytki.utils.NotificationCreator
import io.feelfree.estimotezabytki.ui.congratss.startCongratsActivity
import io.feelfree.estimotezabytki.ui.quizactivity.QuizActivity
import io.feelfree.estimotezabytki.utils.TrailManager
import kotlinx.android.synthetic.main.activity_beaconlocation.*

fun Context.openBeaconLocationActivity(trail : TrailResponse) {
    val intent = Intent(this, TrailBeaconLocationActivity::class.java)
    intent.putExtra(TrailBeaconLocationActivity.EXTRA_TRAIL, trail)
    startActivity(intent)
}

class TrailBeaconLocationActivity : BaseActivity() {
    companion object {
        val EXTRA_TRAIL = "EXTRA_TRAIL"
        val PERMISSION_REQUESTCODE = 15
    }
    val trail by lazy { intent.getParcelableExtra<TrailResponse>(EXTRA_TRAIL) }
    var scanHandler : EstimoteMonitor.Handler? = null
    var trailManager = TrailManager()
    var correctPoints = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beaconlocation)
        trailManager.startTrail(trail)
        showTrailInformations()
        supportActionBar?.title = "Twój cel"

        openQuiz.visibility = View.GONE
        checkPermissions()
    }

    fun startMonitoringForId(id : String) {
        scanHandler?.stop()
        val estimotemonitor = MonitorFactory().createEstimoteMonitor(this)
        val role = estimotemonitor.zoneBuilder()
                .forSingleIdentifier(id)
                .withOnEnterAction {
                    trailManager.flagCurrentBeacon()
                    openQuiz.visibility = View.VISIBLE
                    openQuiz.setOnClickListener {
                        val intent = Intent(this, QuizActivity::class.java)
                        intent.putExtra(QuizActivity.BEACON_EXTRA, trailManager.getCurrentBeacon())
                        startActivityForResult(intent, QuizActivity.RESPONSE_CODE)
                    }
                }
                .withOnErrorAction {

                }
                .withOnExitAction {

                }
                .create()
        scanHandler = estimotemonitor.addMonitoringZone(role).withBalancedPowerMode().startWithScannerInForegroundService(NotificationCreator().create(this))
    }

    fun showTrailInformations() {
        val beacon = trailManager.getCurrentBeacon()
        beaconName.text = beacon.title
        coordinates.text = "${beacon.longitude}, ${beacon.latitude}"
        launchMapButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${beacon.latitude},${beacon.longitude}"))
            intent.`package` = "com.google.android.apps.maps"
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scanHandler?.stop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == QuizActivity.RESPONSE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    correctPoints += data.getIntExtra(QuizActivity.EXTRA_CORRECT_ANSWERS, 0)
                    trailManager.currentStep++
                    if (trailManager.currentStep < trailManager.trail.beacons.size) {
                        openQuiz.visibility = View.GONE
                        startMonitoringForId(trailManager.getCurrentBeacon().id)
                        showTrailInformations()
                    } else {
                        startCongratsActivity(correctPoints)
                        finish()
                    }
                }
            }
        }
    }

    fun checkPermissions() {
        if (checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_REQUESTCODE )
        } else startMonitoringForId(trailManager.getCurrentBeacon().id)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUESTCODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startMonitoringForId(trailManager.getCurrentBeacon().id)
            }
        }
    }

}