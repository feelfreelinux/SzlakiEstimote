package io.feelfree.estimotezabytki

import android.app.Application
import io.feelfree.estimotezabytki.di.components.DaggerInjector
import io.feelfree.estimotezabytki.di.components.Injector
import io.feelfree.estimotezabytki.di.modules.AppModule
import io.feelfree.estimotezabytki.di.modules.NetworkModule
import io.feelfree.estimotezabytki.di.modules.RepositoryModule

class App : Application() {
    companion object {
        lateinit var uiInjector : Injector
        val BASE_URL = "https://gist.githubusercontent.com"
    }

    override fun onCreate() {
        super.onCreate()

        uiInjector = DaggerInjector.builder()
                .appModule(AppModule(this))
                .repositoryModule(RepositoryModule())
                .networkModule(NetworkModule(BASE_URL))
                .build()
    }
}