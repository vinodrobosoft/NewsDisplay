package com.robosoft.newsapp.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.robosoft.newsapp.R
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import com.robosoft.newsapp.databinding.ListItemsBinding

class NewsViewHolder(private val listItemsBinding: ListItemsBinding) :
        RecyclerView.ViewHolder(listItemsBinding.root)
        {
            fun bind(news: NewsResponse.NewsTop) {
                with(news) {
                    listItemsBinding.popularNewsTitle.text = title
                    listItemsBinding.popularNewsHeadline.text = content
                    val source1 = source.toString()
                    val source2 = source1.split("=").toTypedArray()
                    listItemsBinding.newsSource.text = source2.get(2).removeSuffix("}")
                    listItemsBinding.popularNewsImage.load(urlToImage) {
                        crossfade(true)
                    }
                }
            }

            companion object {
                fun create(viewGroup : ViewGroup) : NewsViewHolder {
                    val view = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.list_items,  viewGroup,false)

                    val binding = ListItemsBinding.bind(view)

                    return NewsViewHolder(
                        binding
                    )
                }
            }
        }