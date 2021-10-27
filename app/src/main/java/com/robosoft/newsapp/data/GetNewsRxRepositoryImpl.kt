package com.robosoft.newsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import com.robosoft.newsapp.data.datasource.NewsDataSource
import io.reactivex.Flowable

class GetNewsRxRepositoryImpl(private val pagingSource: NewsDataSource):
        GetNewsRxRepository {

    override fun getNews(): Flowable<PagingData<NewsResponse.NewsTop>> {

        return Pager(
                    config = PagingConfig(
                            pageSize = 20,
                            enablePlaceholders = true,
                            maxSize = 30,
                            prefetchDistance = 5,
                            initialLoadSize = 10),
                    pagingSourceFactory = { pagingSource }
                    ).flowable
    }
}