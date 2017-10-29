package io.feelfree.estimotezabytki.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.feelfree.estimotezabytki.ui.adapter.viewholder.TrailViewholder

class TrailListAdapter : RecyclerView.Adapter<TrailViewholder>() {
    var trails : ArrayList<TrailResponse> = arrayListOf()
    override fun getItemCount() = trails.size

    override fun onBindViewHolder(holder: TrailViewholder?, position: Int) {
        holder?.bindView(trails[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailViewholder {
        return TrailViewholder(LayoutInflater.from(parent.context).inflate(R.layout.track_element, parent, false))
    }
}