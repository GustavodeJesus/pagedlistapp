package com.gustavo.pagedlistappandroid.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.gustavo.pagedlistappandroid.api.response.Result


class ComicDataSourceFactory : DataSource.Factory<Int, Result>() {

    val comicLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Result>>()

    override fun create(): DataSource<Int, Result> {
        val comicDataSource = ComicDataSource()

        comicLiveDataSource.postValue(comicDataSource)

        return comicDataSource
    }

}