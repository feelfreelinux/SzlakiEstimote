package io.feelfree.estimotezabytki.models.pojo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize data class TrailResponse(
        @SerializedName("name")
        val name : String,

        @SerializedName("description")
        val description : String,

        @SerializedName("city")
        val city : String,

        @SerializedName("beacons")
        val beacons : List<BeaconResponse>
) : Parcelable