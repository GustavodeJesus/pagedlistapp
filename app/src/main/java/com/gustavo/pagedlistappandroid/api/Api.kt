package com.gustavo.pagedlistappandroid.api

import com.gustavo.pagedlistappandroid.api.response.Comic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("issues/?field_list=name,image,date_last_updated,id,volume&format=json")
    fun getAnswers(
        @Query("offset") page: Int,
        @Query("limit") pagesize: Int,
        @Query("api_key") site: String
    ): Call<Comic>
}