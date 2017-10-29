package io.feelfree.estimotezabytki.api.trails

import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.reactivex.Single
import retrofit2.Retrofit

interface TrailsApi {
    fun getTrails() : Single<List<TrailResponse>>
    fun getBeacons(): Single<List<BeaconResponse>>
}

class TrailsRepository(val retrofit : Retrofit) : TrailsApi {
    val trailsApi by lazy { retrofit.create(TrailsRetrofitApi::class.java) }

    override fun getTrails() = trailsApi.getTrails()

    override fun getBeacons() = trailsApi.getBeacons()
}