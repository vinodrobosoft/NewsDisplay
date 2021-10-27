package com.robosoft.newsapp.data

import androidx.paging.PagingData
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import io.reactivex.Flowable

interface GetNewsRxRepository {

    fun getNews(): Flowable<PagingData<NewsResponse.NewsTop>>
}