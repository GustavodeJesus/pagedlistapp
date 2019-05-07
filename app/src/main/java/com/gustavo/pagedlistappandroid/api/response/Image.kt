package com.gustavo.pagedlistappandroid.api.response

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("small_url")
    var small_url: String
)