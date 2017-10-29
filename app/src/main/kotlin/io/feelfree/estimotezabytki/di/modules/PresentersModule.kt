package io.feelfree.estimotezabytki.di.modules

import dagger.Module
import dagger.Provides
import io.feelfree.estimotezabytki.api.trails.TrailsApi
import io.feelfree.estimotezabytki.ui.beaconlist.BeaconListPresenter
import io.feelfree.estimotezabytki.ui.trailslist.TrailsListPresenter
import io.feelfree.estimotezabytki.utils.rx.ExecutionSchedulers

@Module
class PresentersModule {
    @Provides
    fun provideTrailsListPresenter(executionSchedulers: ExecutionSchedulers, trailsApi: TrailsApi) = TrailsListPresenter(executionSchedulers, trailsApi)

    @Provides
    fun provideBeaconListPresenter(executionSchedulers: ExecutionSchedulers, trailsApi: TrailsApi) = BeaconListPresenter(executionSchedulers, trailsApi)
}