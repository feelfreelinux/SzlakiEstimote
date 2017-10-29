package io.feelfree.estimotezabytki.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import kotlinx.android.synthetic.main.beacon_list_item.view.*

class BeaconListAdapter(context: Context, val list : ArrayList<BeaconResponse>) : ArrayAdapter<BeaconResponse>(context, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView ?: LayoutInflater.from(context).inflate(R.layout.beacon_list_item, parent, false)
        val item = list[position]
        view.title.text = item.title
        if (item.questions != null) {
            view.questions.text = "${item.questions!!.size} pytań"
        } else view.questions.visibility = View.GONE
        return view
    }
}