package io.feelfree.estimotezabytki.models.pojo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize data class BeaconResponse(
        @SerializedName("id")
        val id : String,

        @SerializedName("title")
        val title : String,

        @SerializedName("longitude")
        val longitude : String,

        @SerializedName("latitude")
        val latitude : String,

        @SerializedName("description")
        val description : String,

        @SerializedName("questions")
        var questions : List<QuestionResponse>?
) : Parcelable