package io.feelfree.estimotezabytki.api.trails

import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.reactivex.Single
import retrofit2.http.GET

interface TrailsRetrofitApi {
    @GET("/feelfreelinux/c45bef3bcb63a27c813f7cb16297c6a4/raw/1fa3e3ccffc0d08189ae651be703986aec5234de/response.json")
    fun getTrails() : Single<List<TrailResponse>>

    @GET("/feelfreelinux/47cad9d961fa4c45309920a5e155a261/raw/288f1b2973aa13cda232ad5eab69cbe3bf38db53/beacons.json")
    fun getBeacons() : Single<List<BeaconResponse>>
}