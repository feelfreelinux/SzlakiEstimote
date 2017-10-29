package io.feelfree.estimotezabytki.api.trails

import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import io.feelfree.estimotezabytki.models.pojo.TrailResponse
import io.reactivex.Single
import retrofit2.http.GET

interface TrailsRetrofitApi {
    @GET("/feelfreelinux/c45bef3bcb63a27c813f7cb16297c6a4/raw/b8d8421c6004ab75367283c12d143ee9a777ae33/response.json")
    fun getTrails() : Single<List<TrailResponse>>

    @GET("/feelfreelinux/47cad9d961fa4c45309920a5e155a261/raw/91a55cdc3e62b7c7151c786d81b515e4602580f3/beacons.json")
    fun getBeacons() : Single<List<BeaconResponse>>
}