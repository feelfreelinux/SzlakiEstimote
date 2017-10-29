package io.feelfree.estimotezabytki.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.feelfree.estimotezabytki.ui.dialogs.TrailsDetailsDialog
import kotlinx.android.synthetic.main.track_element.view.*

class TrailViewholder(val view : View) : RecyclerView.ViewHolder(view) {
    fun bindView(trail: TrailResponse) {
        view.trailCity.text = trail.city
        view.trailTitle.text = trail.name
        view.trailSteps.text = "${trail.beacons.size} miejsc"

        view.setOnClickListener {
            TrailsDetailsDialog(view.context, trail)?.show()
        }
    }
}