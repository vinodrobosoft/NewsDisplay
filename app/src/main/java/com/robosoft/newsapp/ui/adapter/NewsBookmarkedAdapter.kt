package com.robosoft.newsapp.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.robosoft.newsapp.R
import com.robosoft.newsapp.extras.Extras
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.model.NewsArticleDetails
import com.robosoft.newsapp.ui.viewholder.NewsViewHolder
import io.reactivex.subjects.PublishSubject
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import java.lang.IllegalStateException

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

        val bookmarkedImage : AppCompatImageView =
            holder.itemView.findViewById(R.id.news_bookmarked)
        bookmarkedImage.setOnClickListener {
            val bookmarkedNews = getItem(position)
            NDLogs.debug(TAG," News Top position ${bookmarkedNews?.mTitle}")
            deleteBookmarked(bookmarkedNews?.mTitle)
        }
    }

    fun deleteBookmarked(bookmarkedNewsTitle:String?) {
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync {
                try {

                    it.where(NewsArticleDetails::class.java)
                        .equalTo(Extras.TITLE,bookmarkedNewsTitle)
                        .findAll()
                        .deleteAllFromRealm()

                } catch( exception: IllegalStateException) {
                    NDLogs.error(TAG,
                        " ${exception.stackTrace}", Throwable("exception.stackTrace")
                    )
                }
            }

        }
    }

}