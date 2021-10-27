package com.robosoft.newsapp.data.dataresponse

import androidx.core.app.NotificationCompat
import com.google.gson.annotations.SerializedName

data class NewsResponse( @SerializedName("total_pages") val total: Int = 10,
                         val page: Int = 5,
                         val results: List<NewsTop>){

    data class NewsTop(

            @SerializedName("status")
            val status: String,

            @SerializedName("totalResults")
            val totalResults: String,

            @SerializedName("articles")
            val articles: String,

            @SerializedName("source")
            val source: String,

            @SerializedName("author")
            val author: String,

            @SerializedName("content")
            val content: String,

            @SerializedName("description")
            val description: String,

            @SerializedName("publishedAt")
            val publishedAt: String,

            @SerializedName("title")
            val title: String,

            @SerializedName("url")
            val url: String,

            @SerializedName("urlToImage")
            val urlToImage: String
    )

}
