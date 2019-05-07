package com.gustavo.pagedlistappandroid.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.gustavo.pagedlistappandroid.api.response.Result
import com.gustavo.pagedlistappandroid.data.ComicDataSource.Companion.LIMIT
import com.gustavo.pagedlistappandroid.data.ComicDataSourceFactory

class ComicViewModel : ViewModel() {

    internal var resultPagedList: LiveData<PagedList<Result>>
    internal var resultDataSource: LiveData<PageKeyedDataSource<Int, Result>>


    init {
        val comicDataSourceFactory = ComicDataSourceFactory()

        resultDataSource = comicDataSourceFactory.comicLiveDataSource

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(LIMIT).build()

        resultPagedList = LivePagedListBuilder(comicDataSourceFactory, pagedListConfig)
            .build()
    }
    
}