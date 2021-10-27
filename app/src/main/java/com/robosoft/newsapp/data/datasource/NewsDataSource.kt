package com.robosoft.newsapp.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.robosoft.newsapp.api.APIEndPoints
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class NewsDataSource(val apiEndPoints:APIEndPoints,
            private val apiKey: String,
            private val locale: Locale) :
        RxPagingSource<Int,NewsResponse.NewsTop>()  {


    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, NewsResponse.NewsTop>> {
            val position = params.key ?: 1

            return apiEndPoints.topHeadlines(apiKey,locale.language)
                    .subscribeOn(Schedulers.io())
                    .map { toLoadResult(it,position) }
                    .onErrorReturn { LoadResult.Error(it) }
    }


    private fun toLoadResult(data: NewsResponse, position: Int): LoadResult<Int, NewsResponse.NewsTop> {
        return LoadResult.Page(
                data = data.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == data.total) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, NewsResponse.NewsTop>): Int? {
        TODO("Not yet implemented")
    }
}