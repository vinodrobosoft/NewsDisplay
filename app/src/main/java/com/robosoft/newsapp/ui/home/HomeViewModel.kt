package com.robosoft.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.robosoft.newsapp.data.GetNewsRxRepository
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import io.reactivex.Flowable


class HomeViewModel(private val repository: GetNewsRxRepository) : ViewModel() {

    fun newsList() : Flowable<PagingData<NewsResponse.NewsTop>> {
        return repository.getNews()
            .map { pagingData -> pagingData.filter {
                it.author != null
            } }
            .cachedIn(viewModelScope)
    }
}