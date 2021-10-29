package com.robosoft.newsapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.robosoft.newsapp.R
import com.robosoft.newsapp.Util.utility
import com.robosoft.newsapp.api.APIEndPoints
import com.robosoft.newsapp.databinding.FragmentHomeBinding
import com.robosoft.newsapp.extras.StatusTypes
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.ui.adapter.NewsListAdapter
import com.robosoft.newsapp.ui.di.Inject
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.util.logging.Handler

/**
 * A Home [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    val TAG = "HomeFragment"

    lateinit var newsListAdapter: NewsListAdapter
    private var _binding: FragmentHomeBinding? = null

    private val mDisposable = CompositeDisposable()

    //val homeViewModel by viewModel<HomeViewModel>()
    lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        homeViewModel = ViewModelProvider(this, Inject.provideHomeViewModel(view.context)).get(
            HomeViewModel::class.java)
        setUiValues()
        setUpNewsListAdapter()
        setTopNewsUi()
        setOnClickListener()
        setObservables()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            it.findViewById<AppCompatTextView>(R.id.web_view_url)?.let {
                it.isGone = true
            }
        }
    }

    fun setObservables() {

        mDisposable.add(
            APIEndPoints.responseNewsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    var success = it.second.message
                    when(success) {
                        StatusTypes.SUCCESS.getMessage() -> {

                            NDLogs.debug(TAG ," SUCCESS ")

                            homeViewModel.topNewsList = arrayOf("title",
                                "content",
                                "source",
                                "image",
                                "url")
                            it.first?.get(5)?.title?.let { it1 ->
                                homeViewModel.topNewsList?.set(0,
                                    it1
                                )
                            }

                            it.first?.get(5)?.content?.let { it1 ->
                                homeViewModel.topNewsList?.set(1,
                                    it1
                                )
                            }

                            it.first?.get(5)?.source?.let { it1 ->
                                val resultedSource = utility.splitSourceString(
                                    it1.toString())
                                homeViewModel.topNewsList?.set(2,
                                    resultedSource
                                )
                            }

                            it.first?.get(5)?.urlToImage?.let { it1 ->
                                homeViewModel.topNewsList?.set(3,
                                    it1
                                )
                            }

                            it.first?.get(5)?.url?.let { it1 ->
                                homeViewModel.topNewsList?.set(4,
                                    it1
                                )
                            }

                            setTopNewsUi()
                        }
                    }
                }, {
                    NDLogs.error(TAG," ERROR ", error = Throwable(message = "${it.toString()}"))
                })
        )

        mDisposable.add(
            newsListAdapter.itemClickObserver
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    NDLogs.debug(TAG,"Item Clicked inside NewsList ")
                }, {
                    NDLogs.debug(TAG,"Error caused on Item Clicked inside NewsList ${it}")
                } )
        )
    }

    fun setUpNewsListAdapter() {
        newsListAdapter = NewsListAdapter()
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter
        }

        setUpView()
    }

    fun setUpView() {

        mDisposable.add(homeViewModel.newsList().subscribe {

            newsListAdapter.submitData(lifecycle,it)
            //homeViewModel.pagingData = it
            NDLogs.debug(TAG," Submit Data ${newsListAdapter.snapshot().forEach {
                NDLogs.debug(TAG," For Each ${it.toString()}")
            }}")
        })
    }

    fun setTopNewsUi() {
        homeViewModel.topNewsList?.let {

            NDLogs.debug(TAG," setTopNewsUi ")
            it.forEach {
                NDLogs.info(TAG ," ${it} ")
            }

            binding.topNewsHeadline.text = it[0].toString()
            binding.topNewsContent.text = it[1].toString()
            binding.topNewsSource.text = it[2].toString()
            binding.imageTopNews.load(it[3]) {
                crossfade(true)
            }
        }
    }

    fun setOnClickListener() {

        binding.topNewsHeadline.setOnClickListener {
            NDLogs.info(TAG," topNewsHeadlineClickListener ")
            sendBundleValues()
        }
        binding.topNewsContent.setOnClickListener {
            NDLogs.info(TAG," topNewsContentClickListener ")

            sendBundleValues()
        }

        binding.imageTopNews.setOnClickListener {
            NDLogs.info(TAG," imageTopNewsClickListener ")

            sendBundleValues()
        }
    }

    fun sendBundleValues() {
        val bundle =
            bundleOf("url" to (homeViewModel.topNewsList?.get(4) ?: ""))
        findNavController().navigate(R.id.action_HomeFragment_to_HomeDetailsFragment,bundle)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        NDLogs.debug(TAG," onDestroyView ")
        _binding = null
        mDisposable.dispose()
    }
}