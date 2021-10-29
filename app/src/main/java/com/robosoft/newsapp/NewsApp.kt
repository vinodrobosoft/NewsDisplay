package com.robosoft.newsapp

import android.app.Application
import com.robosoft.newsapp.api.APIEndPoints
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.ui.di.appModule
import com.robosoft.newsapp.ui.di.networkModule
import com.robosoft.newsapp.ui.di.viewModelModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {

    val TAG = "NewsApp"
    var apiEndPoints: APIEndPoints? = null

    override fun onCreate() {
        super.onCreate()

        initialise()
        initialiseRealm()
    }

    private fun initialise() {

        startKoin {
            androidContext(this@NewsApp)
            modules(listOf(
                appModule, networkModule, viewModelModule
            ))
        }
    }

    fun initialiseRealm() {

        NDLogs.debug(TAG," initialiseRealm ")
        Realm.init(this)

        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        )

    }
}