package io.feelfree.estimotezabytki.ui.trailslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import io.feelfree.estimotezabytki.App
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.base.BaseActivity
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.feelfree.estimotezabytki.ui.adapter.TrailListAdapter
import io.feelfree.estimotezabytki.ui.dialogs.UploadedLinkDialog
import io.feelfree.estimotezabytki.ui.openTrailEditorActivity
import io.feelfree.estimotezabytki.utils.prepare
import kotlinx.android.synthetic.main.activity_trailslist.*
import javax.inject.Inject

fun Context.openTrailsListActivity(url : String) {
    val intent = Intent(this, TrailsListActivity::class.java)
    intent.putExtra(TrailsListActivity.EXTRA_UPLOADED_URL, url)
    startActivity(intent)
}

class TrailsListActivity : BaseActivity(), TrailsListView {
    @Inject lateinit var presenter : TrailsListPresenter
    val adapter by lazy { TrailListAdapter() }

    companion object {
        val EXTRA_UPLOADED_URL = "EXTRA_UPLOADED_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        App.uiInjector.inject(this)
        setContentView(R.layout.activity_trailslist)
        loadingView.visibility = View.VISIBLE
        supportActionBar?.title = "Dostępne szlaki"
        presenter.subscribe(this)
        if (intent.hasExtra(EXTRA_UPLOADED_URL)) {
            UploadedLinkDialog(this, intent.getStringExtra(EXTRA_UPLOADED_URL)).show()
        }

        trailslist.apply {
            prepare()
            adapter = this@TrailsListActivity.adapter
        }

        createNewTrail.setOnClickListener {
            openTrailEditorActivity()
        }
        presenter.fetchTrails()
    }

    override fun showTrailsList(trails: List<TrailResponse>) {
        loadingView.visibility = View.GONE
        adapter.trails.clear()
        adapter.trails.addAll(trails)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}