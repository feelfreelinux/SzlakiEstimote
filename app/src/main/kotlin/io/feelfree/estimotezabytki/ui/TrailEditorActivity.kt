package io.feelfree.estimotezabytki.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.base.BaseActivity
import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import io.feelfree.estimotezabytki.models.pojo.QuestionResponse
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.feelfree.estimotezabytki.ui.adapter.BeaconListAdapter
import io.feelfree.estimotezabytki.ui.beaconlist.BeaconListActivity
import io.feelfree.estimotezabytki.ui.dialogs.AddQuestionDialog
import io.feelfree.estimotezabytki.ui.dialogs.UploadedLinkDialog
import io.feelfree.estimotezabytki.ui.trailslist.openTrailsListActivity
import io.feelfree.estimotezabytki.utils.GistUploader
import io.feelfree.estimotezabytki.utils.rx.ApplicationSchedulers
import kotlinx.android.synthetic.main.beaconeditorheader.view.*
import kotlinx.android.synthetic.main.traileditoractivity.*
import java.util.*

fun Context.openTrailEditorActivity() {
    val intent = Intent(this, TrailEditorActivity::class.java)
    startActivity(intent)
}

class TrailEditorActivity : BaseActivity() {
    val editorHeader by lazy { layoutInflater.inflate(R.layout.beaconeditorheader, null, false) }
    val adapter by lazy { BeaconListAdapter(this, arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.traileditoractivity)
        supportActionBar?.title = "Stwórz szlak"
        loadingView.visibility = View.GONE
        beaconsList.addHeaderView(editorHeader)
        beaconsList.adapter = this.adapter
        beaconsList.setOnItemClickListener { _, _, i, _ ->
            run {
                AddQuestionDialog(this@TrailEditorActivity, {
                    val beacon = adapter.list[i-1]
                    if (beacon.questions == null) {
                        beacon.questions = arrayListOf()
                    }
                    val items = arrayListOf<QuestionResponse>()
                    items.addAll(beacon.questions!!)
                    items.add(it)
                    beacon.questions = items
                }).show()
            }
        }
        addBeacon.setOnClickListener {
            val intent = Intent(this, BeaconListActivity::class.java)
            startActivityForResult(intent, BeaconListActivity.ITEM_SELECTED)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editormenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.save -> createTrail()
            android.R.id.home -> finish()
        }
        return true
    }

    fun addBeacon(beaconResponse: BeaconResponse) {
        adapter.add(beaconResponse)
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BeaconListActivity.ITEM_SELECTED) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    addBeacon(data.getParcelableExtra(BeaconListActivity.ITEM_BEACON))
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun createTrail() {
        val trail = TrailResponse(editorHeader.title.toString(), editorHeader.description.toString(), editorHeader.city.toString(), adapter.list)
        val json = Gson().toJson(trail)
        val schedulers = ApplicationSchedulers()
        loadingView.visibility = View.VISIBLE
        beaconsList.visibility = View.INVISIBLE
        GistUploader().uploadGist(json).observeOn(schedulers.observeScheduler()).subscribeOn(schedulers.subscribeScheduler())
                .subscribe({
                    openTrailsListActivity(it)
                }, {
                    showErrorDialog(it)
                    loadingView.visibility = View.GONE
                    beaconsList.visibility = View.VISIBLE
                })
    }
}