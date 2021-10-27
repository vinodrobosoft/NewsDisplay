package com.robosoft.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.robosoft.newsapp.data.GetNewsRxRepository
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import io.reactivex.Flowable


class HomeViewModel(val repository: GetNewsRxRepository) : ViewModel() {

    /*val newsList = Pager(PagingConfig(pageSize = 6)) {
        NewsDataSource(apiEndPoints)
    }.flowable.cachedIn(viewModelScope)*/


    fun newsList() : Flowable<PagingData<NewsResponse.NewsTop>> {
        return repository.getNews()
            .cachedIn(viewModelScope)
    }
}