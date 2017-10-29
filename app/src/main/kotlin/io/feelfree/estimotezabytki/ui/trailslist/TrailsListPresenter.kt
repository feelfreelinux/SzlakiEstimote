package io.feelfree.estimotezabytki.ui.trailslist

import android.util.Log
import io.feelfree.estimotezabytki.api.trails.TrailsApi
import io.feelfree.estimotezabytki.base.BasePresenter
import io.feelfree.estimotezabytki.utils.rx.ExecutionSchedulers

class TrailsListPresenter(val schedulers: ExecutionSchedulers, val trailsApi: TrailsApi) : BasePresenter<TrailsListView>() {
    fun fetchTrails() {
        subscriptions.add(
                trailsApi.getTrails()
                        .observeOn(schedulers.observeScheduler())
                        .subscribeOn(schedulers.subscribeScheduler())
                        .subscribe(
                                {
                                    view?.showTrailsList(it)
                                },
                                { view?.showErrorDialog(it) })
        )
    }
}