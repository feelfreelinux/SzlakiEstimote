package io.feelfree.estimotezabytki.ui.beaconlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import io.feelfree.estimotezabytki.App
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.base.BaseActivity
import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import io.feelfree.estimotezabytki.ui.adapter.BeaconListAdapter
import kotlinx.android.synthetic.main.activity_beaconlist.*
import javax.inject.Inject

class BeaconListActivity : BaseActivity(), BeaconListView {
    @Inject lateinit var presenter : BeaconListPresenter
    val adapter by lazy { BeaconListAdapter(this, arrayListOf()) }

    companion object {
        val ITEM_SELECTED = 221
        val ITEM_BEACON = "EXTRA_ITEM_BEACON"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.uiInjector.inject(this)
        setContentView(R.layout.activity_beaconlist)
        loadingView.visibility = View.VISIBLE
        supportActionBar?.title = "Dodaj miejsce"
        listView.adapter = this.adapter
        presenter.subscribe(this)
        presenter.fetchBeacons()
        listView.setOnItemClickListener( { _, _, pos, _ ->
            run {
                val intent = Intent()
                intent.putExtra(ITEM_BEACON, adapter.getItem(pos))
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
    }

    override fun showBeacons(items: List<BeaconResponse>) {
        loadingView.visibility = View.GONE
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
    }
}