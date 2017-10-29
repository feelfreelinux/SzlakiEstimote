package io.feelfree.estimotezabytki.di.modules

import dagger.Module
import dagger.Provides
import io.feelfree.estimotezabytki.utils.rx.ApplicationSchedulers
import io.feelfree.estimotezabytki.utils.rx.ExecutionSchedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(val baseUrl : String) {
    @Provides
    fun provideRetrofit() : Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    fun provideApplicationSchedulers() : ExecutionSchedulers = ApplicationSchedulers()
}