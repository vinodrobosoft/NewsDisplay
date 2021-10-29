package com.robosoft.newsapp.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.ui.viewholder.NewsViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class NewsListAdapter :
        PagingDataAdapter<NewsResponse.NewsTop,
                NewsViewHolder>(NewsDifferentiator) {

    val TAG = "NewsListAdapter"
    val itemClick  = PublishSubject.create<NewsResponse.NewsTop>()
    val itemClickObserver: Observable<NewsResponse.NewsTop>
        get() = itemClick.share()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NewsViewHolder {

        NDLogs.debug(TAG," onCreateViewHolder ")
        val newsViewHolder =  NewsViewHolder.create(
            parent,itemClick,viewType )
        return newsViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        NDLogs.debug(TAG," onBindViewHolder ")
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        //NDLogs.debug(TAG," getItemCount ${super.getItemCount()} ")
        return super.getItemCount()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        NDLogs.debug(TAG," onAttachedToRecyclerView ")
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemViewType(position: Int): Int {

        /*NDLogs.debug(TAG," getItemViewType " +
                "${super.getItemViewType(position)} ")*/
        return super.getItemViewType(position)
    }

    object NewsDifferentiator : DiffUtil.ItemCallback<NewsResponse.NewsTop>() {
        override fun areItemsTheSame(oldItem: NewsResponse.NewsTop, newItem: NewsResponse.NewsTop): Boolean {
            NDLogs.debug("NewsDifferentiator"," areItemsTheSame ")
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsResponse.NewsTop, newItem: NewsResponse.NewsTop): Boolean {
            NDLogs.debug("NewsDifferentiator"," areContentsTheSame ")
            return oldItem == newItem
        }
    }
}