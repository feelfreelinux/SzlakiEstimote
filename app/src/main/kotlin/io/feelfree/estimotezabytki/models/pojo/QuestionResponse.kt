package io.feelfree.estimotezabytki.models.pojo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize data class QuestionResponse(
        @SerializedName("question")
        val question : String,

        @SerializedName("answers")
        val answers : List<String>,

        @SerializedName("right_answer_index")
        val rightAnswerIndex : Int
) : Parcelable