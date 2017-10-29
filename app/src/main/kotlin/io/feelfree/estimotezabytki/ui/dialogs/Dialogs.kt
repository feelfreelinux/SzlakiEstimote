package io.feelfree.estimotezabytki.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.models.pojo.QuestionResponse
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.feelfree.estimotezabytki.ui.trailbeaconlocation.openBeaconLocationActivity
import kotlinx.android.synthetic.main.add_question_dialog.view.*
import kotlinx.android.synthetic.main.dialog_trail_details.view.*
import kotlinx.android.synthetic.main.url_dialog.view.*

@Suppress("DEPRECATION")
fun Context.createAlertBuilder() : AlertDialog.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1)
            AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert)
        else AlertDialog.Builder(this, android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK)

fun TrailsDetailsDialog(context: Context, trail : TrailResponse): AlertDialog? {
    val builder = context.createAlertBuilder()
    val view = LayoutInflater.from(context).inflate(R.layout.dialog_trail_details, null, false)
    view.apply {
        trailTitle.text = trail.name
        trailCity.text = trail.city
        trailDescription.text = trail.description
        startTrail.setOnClickListener {
            context.openBeaconLocationActivity(trail)
        }
    }

    builder.apply {
        setTitle("Szczegóły szlaku")
        setView(view)
    }
    return builder.create()
}

fun AddQuestionDialog(context: Context, callback : (QuestionResponse) -> Unit): AlertDialog.Builder {
    val alertBuilder = context.createAlertBuilder()
    val view = LayoutInflater.from(context).inflate(R.layout.add_question_dialog, null, false)
    var right_answer_index = 0
    view.odpARadio.setOnCheckedChangeListener { _, b ->
        if (b) {
            right_answer_index = 0
        }
    }

    view.odpBRadio.setOnCheckedChangeListener { _, b ->
        if (b) {
            right_answer_index = 1
        }
    }

    view.odpCradio.setOnCheckedChangeListener { _, b ->
        if (b) {
            right_answer_index = 2
        }
    }
    return alertBuilder.apply {
        setTitle("Dodaj pytanie")
        setPositiveButton("Dodaj pytanie", {
            _, _ ->
            val item = QuestionResponse(view.pytanie.text.toString(), listOf(view.odpA.text.toString(), view.odpB.text.toString(), view.odpC.text.toString()), right_answer_index)
            callback.invoke(item)
        })
        setView(view)
    }
}
fun UploadedLinkDialog(context: Context, url : String): AlertDialog.Builder {
    val builder = context.createAlertBuilder()
    val view = LayoutInflater.from(context).inflate(R.layout.url_dialog, null, false)

    view.urlView.text = url
    builder.apply {
        setView(view)
        setTitle("Udostępniono szlak")
        setPositiveButton(android.R.string.ok, null)
    }
    return builder
}