package com.robosoft.newsapp

import android.app.Application
import com.robosoft.newsapp.api.APIEndPoints
import com.robosoft.newsapp.ui.di.appModule
import com.robosoft.newsapp.ui.di.networkModule
import com.robosoft.newsapp.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {

    var apiEndPoints: APIEndPoints? = null

    override fun onCreate() {
        super.onCreate()

        initialise()
    }

    private fun initialise() {

        startKoin {
            androidContext(this@NewsApp)
            modules(listOf(
                appModule, networkModule, viewModelModule
            ))
        }
    }
}