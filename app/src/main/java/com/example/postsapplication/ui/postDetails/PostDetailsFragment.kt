package com.example.postsapplication.ui.postDetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.postsapplication.R
import com.example.postsapplication.databinding.PostDetailsFragmentBinding
import com.example.postsapplication.ui.base.BaseFragment
import com.example.postsapplication.ui.postsList.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostDetailsFragment : BaseFragment<PostDetailsFragmentBinding>() {
    private val viewModel by viewModels<PostDetailsViewModel>()
    @Inject lateinit var commentAdapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view_ = setViewModelWithDataBinding(inflater, R.layout.post_details_fragment)
        binding.viewModel = viewModel
        val id = arguments?.getInt("id")
        setCommentList()
        viewModel.getPostAndCommentsWithID(id!!)

        return view_
    }

    private fun setCommentList() {
        binding.commentsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=commentAdapter
        }
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            commentAdapter.submitList(it)
        })
    }


}
