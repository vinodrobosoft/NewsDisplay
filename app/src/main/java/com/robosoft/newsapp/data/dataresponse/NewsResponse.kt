package com.robosoft.newsapp.data.dataresponse

import androidx.core.app.NotificationCompat
import com.google.gson.annotations.SerializedName

data class NewsResponse( @SerializedName("total_pages") val total: Int = 0,
                         val page: Int = 0,
                         val results: List<NewsTop>){

    data class NewsTop(
            @SerializedName("articles")
            val articles: String,

            @SerializedName("author")
            val author: String,

            @SerializedName("content")
            val content: String,

            @SerializedName("description")
            val description: String,

            @SerializedName("publishedAt")
            val publishedAt: String,

            @SerializedName("source")
            val source: String,

            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            val status: String,

            @SerializedName("title")
            val title: String,

            @SerializedName("totalResults")
            val totalResults: String,

            @SerializedName("url")
            val url: String,

            @SerializedName("urlToImage")
            val urlToImage: String
    )

}
