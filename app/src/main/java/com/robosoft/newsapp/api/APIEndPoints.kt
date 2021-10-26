package com.robosoft.newsapp.api

import com.robosoft.newsapp.data.dataresponse.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndPoints {

    @GET(APIEndPointsConstant.TOP_HEADLINES)
    fun topHeadlines(@Query("apiKey") apiKey: String?,
                     @Query("category") category: String?,
                     @Query("language") language: String?,
                     @Query("country") country: String?) : Single<News>
}