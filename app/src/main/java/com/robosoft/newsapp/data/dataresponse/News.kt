package com.robosoft.newsapp.data.dataresponse

import androidx.core.app.NotificationCompat
import com.google.gson.annotations.SerializedName

sealed class News {

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
    ) : News()

    data class NewsSources(
            @SerializedName("category")
            val category: String,

            @SerializedName("country")
            val country: String,

            @SerializedName("description")
            val description: String,

            @SerializedName("id")
            val id: String,

            @SerializedName("language")
            val language: String,

            @SerializedName("name")
            val name: String,

            @SerializedName("sources")
            val sources: String,

            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            val status: String,

            @SerializedName("url")
            val url: String
    ) : News()
}
