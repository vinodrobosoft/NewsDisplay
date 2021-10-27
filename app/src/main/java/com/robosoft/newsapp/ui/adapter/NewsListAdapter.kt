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

class NewsListAdapter :
        PagingDataAdapter<NewsResponse.NewsTop, NewsListAdapter.NewsViewHolder>(NewsDifferentiator) {

    inner class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Log.d("vs","onBindViewHolder Title ${getItem(position)?.title}")
        /*holder.itemView.findViewById<AppCompatTextView>(R.id.popular_news_title)
                .setText(getItem(position)?.title)*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_items,parent,false)

        val viewHolder = NewsViewHolder(newsItem)
        return viewHolder
    }

    object NewsDifferentiator : DiffUtil.ItemCallback<NewsResponse.NewsTop>() {
        override fun areItemsTheSame(oldItem: NewsResponse.NewsTop, newItem: NewsResponse.NewsTop): Boolean {
            return oldItem.author == newItem.author
        }

        override fun areContentsTheSame(oldItem: NewsResponse.NewsTop, newItem: NewsResponse.NewsTop): Boolean {
            return oldItem == newItem
        }

    }


}