package com.robosoft.newsapp.ui.di

import com.robosoft.newsapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModel() {
        HomeViewModel()
    }
}