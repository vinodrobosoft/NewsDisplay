package com.robosoft.newsapp.ui.bookmarked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.robosoft.newsapp.R
import com.robosoft.newsapp.databinding.FragmentBookmarkedBinding
import com.robosoft.newsapp.model.NewsArticleDetails
import com.robosoft.newsapp.ui.adapter.NewsBookmarkedAdapter
import com.robosoft.newsapp.ui.adapter.NewsListAdapter
import io.realm.OrderedRealmCollection
import io.realm.Realm

/**
 * A Bookmarked [Fragment] subclass as to display the bookmarked news.
 */
class BookmarkedFragment : Fragment() {

    val TAG = "BookmarkedFragment"

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }
    private var _binding: FragmentBookmarkedBinding? = null

    lateinit var newsBookmarkedAdapter: NewsBookmarkedAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkedBinding.inflate(inflater, container, false)
        setUiValues()
        initNewsListAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setUiValues() {

        activity?.let {
            it.findViewById<AppCompatImageView>(R.id.global_news_toolbar)?.let {
                it.isGone = true
            }
            it.findViewById<AppCompatImageView>(R.id.bookmarked_toolbar)?.let {
                it.isVisible = true
                it.isEnabled = false
            }
            it.findViewById<AppCompatImageView>(R.id.search_toolbar)?.let {
                it.isVisible = true
                it.isEnabled = false
            }
            it.findViewById<AppCompatEditText>(R.id.search_edit_text)?.let {
                it.isGone = true
            }
            it.findViewById<AppCompatTextView>(R.id.bookmarked_title)?.let {
                it.isVisible = true
            }
        }

    }

    fun initNewsListAdapter() {

        val newsList = realm.where(NewsArticleDetails::class.java).findAllAsync()

        newsBookmarkedAdapter = NewsBookmarkedAdapter(requireContext(),
            newsList as OrderedRealmCollection<NewsArticleDetails>)

        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsBookmarkedAdapter
        }
        with(binding.newsRecyclerView.itemAnimator) {
            if(this is SimpleItemAnimator) {
                this.supportsChangeAnimations = true
            }
        }

        //setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}