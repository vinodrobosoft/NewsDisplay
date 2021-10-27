package com.robosoft.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robosoft.newsapp.data.GetNewsRxRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val repository: GetNewsRxRepository) :
        ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("View Model ERROR")
    }

}