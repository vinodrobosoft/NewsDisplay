package com.robosoft.newsapp.ui.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.robosoft.newsapp.NewsApp
import com.robosoft.newsapp.api.APIEndPoints
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { getBuilder(get()) }

    single(named("CREATE_API_SERVICE")) {
                createService(ctx = get(),
                serviceClass = get<Class<APIEndPoints>>())
    }

    single(named("GET_API_SERVICE")) {
        getApiService(get())
    }

}


private fun getBuilder(context: Context): Retrofit.Builder {
    return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
}

fun <S> createService(ctx: Context, serviceClass: Class<S>): S {
    var retrofit : Retrofit? = getBuilder(ctx)
            .build()
    return retrofit!!.create(serviceClass)
}


fun getApiService(context: Context): APIEndPoints {
    val application = context.applicationContext as NewsApp
    if (application.apiEndPoints == null) {
        application.apiEndPoints =
                createService(application, APIEndPoints::class.java)
    }
    return application.apiEndPoints as APIEndPoints
}




