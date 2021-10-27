package com.robosoft.newsapp.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import com.robosoft.newsapp.extras.Configuration
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndPoints {

   /* @GET(APIEndPointsConstant.TOP_HEADLINES)
    fun topHeadlines(@Query("apiKey") apiKey: String?,
                     @Query("category") category: String?,
                     @Query("language") language: String?,
                     @Query("country") country: String?) : Single<NewsResponse>*/

    @GET(APIEndPointsConstant.TOP_HEADLINES)
    fun topHeadlines(@Query("apiKey") apiKey: String?,
                     @Query("country") country: String?) : Single<NewsResponse>


    companion object {

        fun create(): APIEndPoints {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(Configuration.BASE_URL_NEWS)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(APIEndPoints::class.java)
        }
    }
}