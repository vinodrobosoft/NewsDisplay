package com.robosoft.newsapp.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.model.NewsArticleDetails
import com.robosoft.newsapp.ui.viewholder.NewsViewHolder
import io.reactivex.subjects.PublishSubject
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class NewsBookmarkedAdapter(val context: Context,
data:OrderedRealmCollection<NewsArticleDetails>) :
    RealmRecyclerViewAdapter<NewsArticleDetails,
            NewsViewHolder>(data,true){

    val TAG = "NewsBookmarkedAdapter"

    val clickSubject = PublishSubject.create<NewsArticleDetails>()
    val clickSubjectObservable: io.reactivex.Observable<NewsArticleDetails>?
        get() = clickSubject.share()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        NDLogs.debug(TAG," onCreateViewHolder ")
        val newsViewHolder =  NewsViewHolder.create(
            parent)
        return newsViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        NDLogs.debug(TAG," onBindViewHolder ")

        getItem(position)?.let {
            holder.bindDb(it)
        }
    }
}