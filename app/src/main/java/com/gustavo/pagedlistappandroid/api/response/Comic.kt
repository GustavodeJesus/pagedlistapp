package com.gustavo.pagedlistappandroid.api.response

import com.google.gson.annotations.SerializedName

data class Comic(

    @SerializedName("error")
    var error: String,

    @SerializedName("limit")
    var limit: Int,

    @SerializedName("offset")
    var offset: Int,

    @SerializedName("number_of_page_results")
    var number_of_page_results: Int,

    @SerializedName("number_of_total_results")
    var number_of_total_results: Long,

    @SerializedName("status_code")
    var status_code: Int,

    @SerializedName("results")
    var results: List<Result>,

    @SerializedName("version")
    var version: String


)