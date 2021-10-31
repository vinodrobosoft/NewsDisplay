package com.robosoft.newsapp.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.jakewharton.rxbinding2.view.RxView
import com.robosoft.newsapp.R
import com.robosoft.newsapp.Util.utility
import com.robosoft.newsapp.data.dataresponse.NewsResponse
import com.robosoft.newsapp.databinding.ListItemsBinding
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.model.NewsArticleDetails
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

            fun bindDb(newsArticleDetails: NewsArticleDetails) {
                with(newsArticleDetails) {
                    listItemsBinding.popularNewsTitle.text = newsArticleDetails.mTitle
                    listItemsBinding.popularNewsHeadline.text   =
                        newsArticleDetails.mContent

                    listItemsBinding.newsSource.text =
                        newsArticleDetails.mSource
                    listItemsBinding.popularNewsImage.load(newsArticleDetails.image) {
                        crossfade(true)
                    }
                }
            }

            companion object {
                fun create(
                    viewGroup: ViewGroup
                ) : NewsViewHolder {

                    val view = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.list_items,  viewGroup,false)

                    val binding = ListItemsBinding.bind(view)
                    binding.newsBookmarked.setOnClickListener {

                        NDLogs.debug("NewsViewHolder",
                            " News Bookmarked Clicked ${binding.popularNewsHeadline.text}")
                    }

                    val newsViewHolder = NewsViewHolder(binding)

                    /*RxView.clicks(view)
                        .takeUntil(RxView.detaches(newsViewHolder.itemView))
                        .subscribe{

                        }*/
                    return newsViewHolder
                }
            }
        }