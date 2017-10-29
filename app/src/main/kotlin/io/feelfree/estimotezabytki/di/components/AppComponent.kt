package io.feelfree.estimotezabytki.di.components

import android.app.Application
import android.content.Context
import dagger.Component
import io.feelfree.estimotezabytki.di.modules.AppModule
import io.feelfree.estimotezabytki.di.modules.NetworkModule
import io.feelfree.estimotezabytki.di.modules.PresentersModule
import io.feelfree.estimotezabytki.di.modules.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component( modules = arrayOf(AppModule::class, RepositoryModule::class, NetworkModule::class, PresentersModule::class) )
interface AppComponent {
    val application : Application
    val context : Context
}