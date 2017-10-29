package io.feelfree.estimotezabytki.di.components

import dagger.Component
import io.feelfree.estimotezabytki.di.modules.AppModule
import io.feelfree.estimotezabytki.di.modules.NetworkModule
import io.feelfree.estimotezabytki.di.modules.PresentersModule
import io.feelfree.estimotezabytki.di.modules.RepositoryModule
import io.feelfree.estimotezabytki.ui.beaconlist.BeaconListActivity
import io.feelfree.estimotezabytki.ui.trailslist.TrailsListActivity
import javax.inject.Singleton

@Singleton
@Component( modules = arrayOf(AppModule::class, RepositoryModule::class, NetworkModule::class, PresentersModule::class) )
interface Injector {
    fun inject(trailsListActivity: TrailsListActivity)
    fun inject(trailsListActivity: BeaconListActivity)

}