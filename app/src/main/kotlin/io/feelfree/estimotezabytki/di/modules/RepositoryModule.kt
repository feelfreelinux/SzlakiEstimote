package io.feelfree.estimotezabytki.di.modules

import dagger.Module
import dagger.Provides
import io.feelfree.estimotezabytki.api.trails.TrailsApi
import io.feelfree.estimotezabytki.api.trails.TrailsRepository
import retrofit2.Retrofit

@Module
class RepositoryModule() {
    @Provides
    fun provideTrailsApi(retrofit: Retrofit) : TrailsApi = TrailsRepository(retrofit)
}