package com.robosoft.newsapp.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.jakewharton.rxbinding2.view.RxView
import com.robosoft.newsapp.R
import com.robosoft.newsapp.Util.utility
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import com.robosoft.newsapp.databinding.ListItemsBinding
import com.robosoft.newsapp.logs.NDLogs
import io.reactivex.subjects.PublishSubject

class NewsViewHolder(private val listItemsBinding: ListItemsBinding) :
        RecyclerView.ViewHolder(listItemsBinding.root)
        {
            fun bind(news: NewsResponse.NewsTop) {
                with(news) {
                    listItemsBinding.popularNewsTitle.text = title
                    listItemsBinding.popularNewsHeadline.text = content

                    listItemsBinding.newsSource.text =
                        utility.splitSourceString(source.toString())
                    listItemsBinding.popularNewsImage.load(urlToImage) {
                        crossfade(true)
                    }
                }
            }

            companion object {
                fun create(
                    viewGroup: ViewGroup,
                    itemClick: PublishSubject<NewsResponse.NewsTop>,
                    viewType: Int
                ) : NewsViewHolder {

                    val view = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.list_items,  viewGroup,false)

                    val binding = ListItemsBinding.bind(view)
                    binding.newsBookmarked.setOnClickListener {
                        NDLogs.debug("NewsViewHolder",
                            " News Bookmarked Clicked ")
                    }

                    /*RxView.clicks(view)
                        .takeUntil(RxView.detaches(viewGroup))
                        .map { viewType as NewsResponse.NewsTop }
                        .subscribe(itemClick)*/
                    return NewsViewHolder(
                        binding
                    )
                }
            }
        }