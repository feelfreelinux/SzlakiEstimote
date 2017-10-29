package io.feelfree.estimotezabytki.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.feelfree.estimotezabytki.App

@Module
class AppModule(val app : App) {
    @Provides
    fun provideContext() : Context = app

    @Provides
    fun provideApplication() : Application = app
}