package com.gustavo.pagedlistappandroid.api.response

import com.google.gson.annotations.SerializedName

data class Volume(
    @SerializedName("name")
    var name: String
)