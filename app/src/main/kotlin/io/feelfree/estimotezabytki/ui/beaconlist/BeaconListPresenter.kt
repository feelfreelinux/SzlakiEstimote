package io.feelfree.estimotezabytki.ui.beaconlist

import io.feelfree.estimotezabytki.api.trails.TrailsApi
import io.feelfree.estimotezabytki.base.BasePresenter
import io.feelfree.estimotezabytki.utils.rx.ExecutionSchedulers

class BeaconListPresenter(val executionSchedulers: ExecutionSchedulers, val trailsApi: TrailsApi) : BasePresenter<BeaconListView>() {
    fun fetchBeacons() {
        subscriptions.add(
                trailsApi.getBeacons().subscribeOn(executionSchedulers.subscribeScheduler())
                        .observeOn(executionSchedulers.observeScheduler())
                        .subscribe({view?.showBeacons(it)}, {view?.showErrorDialog(it)})
        )
    }
}