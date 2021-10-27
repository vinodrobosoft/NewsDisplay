package com.robosoft.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.newsapp.R
import com.robosoft.newsapp.databinding.FragmentHomeBinding
import com.robosoft.newsapp.ui.adapter.NewsListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

/**
 * A Home [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    lateinit var newsListAdapter: NewsListAdapter
    private var _binding: FragmentHomeBinding? = null

    private val mDisposable = CompositeDisposable()

    /*val homeViewModel: HomeViewModel by viewModel {
        get(named("HOME_VIEW_MODEL")) }*/

    val homeViewModel : HomeViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUiValues()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpNewsListAdapter()
        //setUpView()
    }

    fun setUpNewsListAdapter() {
        newsListAdapter = NewsListAdapter()
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsListAdapter
        }


    }

    fun setUpView() {

        /*lifecycleScope.launch {
            homeViewModel.newsList.collect {
                newsListAdapter.submitData(it)
            }
        }*/
        mDisposable.add(homeViewModel.newsList().subscribe {
            newsListAdapter.submitData(lifecycle,it)
        })
    }

    fun setUiValues() {
        activity?.let {
            it.findViewById<AppCompatImageView>(R.id.global_news_toolbar)?.let {
                it.isVisible = true
            }
            it.findViewById<AppCompatImageView>(R.id.bookmarked_toolbar)?.let {
                it.isVisible = true
                it.isEnabled = true
            }
            it.findViewById<AppCompatImageView>(R.id.search_toolbar)?.let {
                it.isVisible = true
                it.isEnabled = true
            }
            it.findViewById<AppCompatEditText>(R.id.search_edit_text)?.let {
                it.isGone = true
            }
            it.findViewById<AppCompatTextView>(R.id.bookmarked_title)?.let {
                it.isGone = true
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mDisposable.dispose()
    }
}