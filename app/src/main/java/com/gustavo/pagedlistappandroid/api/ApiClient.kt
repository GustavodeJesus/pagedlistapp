package com.gustavo.pagedlistappandroid.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
    private val retrofit: Retrofit

    val api: Api
        get() = retrofit.create<Api>(Api::class.java)


    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {

        private val BASE_URL = "http://comicvine.gamespot.com/api/"
        private var mInstance: ApiClient? = null

        val instance: ApiClient
            @Synchronized get() {
                if (mInstance == null) {
                    mInstance = ApiClient()
                }
                return mInstance as ApiClient
            }
    }
}