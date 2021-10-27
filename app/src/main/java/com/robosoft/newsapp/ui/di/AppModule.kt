package com.robosoft.newsapp.ui.di

import com.robosoft.newsapp.data.GetNewsRxRepository
import com.robosoft.newsapp.data.GetNewsRxRepositoryImpl
import com.robosoft.newsapp.data.datasource.NewsDataSource
import com.robosoft.newsapp.extras.Configuration
import com.robosoft.newsapp.helper.NewsApiHelper
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

val appModule = module {

    single(named("NEWS_API_KEY")) {
        provideNewsApiKey()
    }

    single(named("BASE_URL_NEWS")) {
        getBaseUrl()
    }

    single(named("GET_LOCALE")) {
        getLocale()
    }

    single(named("NEWS_API_HELPER")) {
        NewsApiHelper(context = get(), apiKey = provideNewsApiKey())
    }


}

fun provideNewsApiKey() = Configuration.NEWS_API_KEY
fun getBaseUrl() = Configuration.BASE_URL_NEWS
fun getLocale() = Locale.UK