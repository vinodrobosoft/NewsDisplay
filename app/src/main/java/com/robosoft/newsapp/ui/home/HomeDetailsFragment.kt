package com.robosoft.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.robosoft.newsapp.R
import com.robosoft.newsapp.databinding.FragmentHomedetailsBinding

/**
 * A HomeDetails [Fragment] subclass as to display the details of the top headlines.
 */
class HomeDetailsFragment : Fragment() {

    private var _binding: FragmentHomedetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomedetailsBinding.inflate(inflater, container, false)
        setUiValues()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* binding.buttonSecond.setOnClickListener {
             findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
         }*/
    }

    fun setUiValues() {

        activity?.let {
            it.findViewById<AppCompatImageView>(R.id.global_news_toolbar)?.let {
                it.isVisible = true
            }
            it.findViewById<AppCompatImageView>(R.id.bookmarked_toolbar)?.let {
                it.isVisible = true
            }
            it.findViewById<AppCompatImageView>(R.id.search_toolbar)?.let {
                it.isVisible = true
            }
            it.findViewById<AppCompatEditText>(R.id.search_edit_text)?.let {
                it.isGone = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}