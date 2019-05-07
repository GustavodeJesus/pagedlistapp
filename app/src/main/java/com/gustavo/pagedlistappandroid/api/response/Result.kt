package com.gustavo.pagedlistappandroid.api.response

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("date_last_updated")
    var date_last_updated: String,

    @SerializedName("image")
    var image: Image,

    @SerializedName("name")
    var name: String,

    @SerializedName("id")
    var id: Long,

    @SerializedName("volume")
    var volume: Volume
)