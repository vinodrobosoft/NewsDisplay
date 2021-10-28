package com.robosoft.newsapp.ui.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.robosoft.newsapp.api.APIEndPoints
import com.robosoft.newsapp.data.GetNewsRxRepositoryImpl
import com.robosoft.newsapp.data.datasource.NewsDataSource
import com.robosoft.newsapp.ui.home.HomeViewModelFactory
import java.util.*

object Inject {

    fun provideLocale(): Locale = Locale.CANADA

    fun provideHomeViewModel(context: Context):
            ViewModelProvider.Factory {

        val pagingSource =
            NewsDataSource(
                apiEndPoints = APIEndPoints.create(),
                apiKey = com.robosoft.newsapp.extras.Configuration.NEWS_API_KEY,
                country = "in"
            )

        val repository =
            GetNewsRxRepositoryImpl(
                pagingSource = pagingSource
            )

        return HomeViewModelFactory(
            repository
        )
    }
}