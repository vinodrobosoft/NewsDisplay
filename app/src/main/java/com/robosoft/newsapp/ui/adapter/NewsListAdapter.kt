package com.robosoft.newsapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.newsapp.R
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import com.robosoft.newsapp.ui.viewholder.NewsViewHolder

class NewsListAdapter :
        PagingDataAdapter<NewsResponse.NewsTop,
                NewsViewHolder>(NewsDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NewsViewHolder {
        return NewsViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    object NewsDifferentiator : DiffUtil.ItemCallback<NewsResponse.NewsTop>() {
        override fun areItemsTheSame(oldItem: NewsResponse.NewsTop, newItem: NewsResponse.NewsTop): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsResponse.NewsTop, newItem: NewsResponse.NewsTop): Boolean {
            return oldItem == newItem
        }
    }
}