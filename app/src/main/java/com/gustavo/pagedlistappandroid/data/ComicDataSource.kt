package com.gustavo.pagedlistappandroid.data

import android.util.Log
import android.widget.Toast
import androidx.paging.PageKeyedDataSource
import com.gustavo.pagedlistappandroid.api.ApiClient
import com.gustavo.pagedlistappandroid.api.response.Comic
import com.gustavo.pagedlistappandroid.api.response.Result
import com.gustavo.pagedlistappandroid.util.Constants.API_KEY
import com.gustavo.pagedlistappandroid.util.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicDataSource :
    PageKeyedDataSource<Int, Result>() {

    private val TAG = ComicDataSource::class.java.simpleName

    companion object {
        // Constantes que são configuradas para serem inseridas nas chamadas das requisições da API
        const val LIMIT = 20
        const val FIRST_PAGE = 1
        const val KEY_API = API_KEY
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        ApiClient.instance
            .api
            .getAnswers(FIRST_PAGE, LIMIT, KEY_API)
            .enqueue(object : Callback<Comic> {
                override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                    if (response.body() != null) {
                        callback.onResult(response.body()!!.results, null, (FIRST_PAGE + 1))
                    }
                    Log.d(TAG, "Pagina Inicial")
                    Log.d(TAG, response.code().toString())
                }

                override fun onFailure(call: Call<Comic>, t: Throwable) {
                    Log.d(TAG, "Pagina Inicial - Failure")
                    Log.d(TAG, t.message)
                    t.printStackTrace()
                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        ApiClient.instance
            .api
            .getAnswers(params.key, LIMIT, KEY_API)
            .enqueue(object : Callback<Comic> {

                override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                    if (response.body() != null) {
                        val key = params.key + 1
                        callback.onResult(response.body()!!.results, key)
                        Toast.makeText(MyApplication.appContext, "Página $key", Toast.LENGTH_SHORT)
                            .show()
                    }

                    Log.d(TAG, "Próxima Pagina")
                    Log.d(TAG, response.code().toString())
                }

                override fun onFailure(call: Call<Comic>, t: Throwable) {
                    Log.d(TAG, "Próxima pagina - Failure")
                    Log.d(TAG, t.message)
                    t.printStackTrace()
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        ApiClient
            .instance
            .api
            .getAnswers(params.key, LIMIT, KEY_API)
            .enqueue(object : Callback<Comic> {

                override fun onResponse(
                    call: Call<Comic>,
                    response: Response<Comic>
                ) {
                    val adjacentKey = if (params.key > 1) {
                        params.key - 1
                    } else
                        null
                    if (response.body() != null)
                        callback.onResult(response.body()!!.results, adjacentKey)

                    Log.d(TAG, "Pagina anterior")
                    Log.d(TAG, response.code().toString())
                }

                override fun onFailure(call: Call<Comic>, t: Throwable) {
                    Log.d(TAG, "Pagina anterior - Failure")
                    Log.d(TAG, t.message)
                    t.printStackTrace()
                }

            })
    }

}