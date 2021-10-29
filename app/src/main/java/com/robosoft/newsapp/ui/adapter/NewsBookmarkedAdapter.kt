package com.robosoft.newsapp.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.newsapp.model.NewsArticleDetails
import io.reactivex.subjects.PublishSubject
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class NewsBookmarkedAdapter(val context: Context,
data:OrderedRealmCollection<NewsArticleDetails>) :
    RealmRecyclerViewAdapter<NewsArticleDetails,
            NewsBookmarkedAdapter.NewsBookmarkedViewHolder>(data,true){


    val clickSubject = PublishSubject.create<NewsArticleDetails>()
    val clickSubjectObservable: io.reactivex.Observable<NewsArticleDetails>?
        get() = clickSubject.share()


    inner class NewsBookmarkedViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        var data: NewsArticleDetails? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsBookmarkedViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewsBookmarkedViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}