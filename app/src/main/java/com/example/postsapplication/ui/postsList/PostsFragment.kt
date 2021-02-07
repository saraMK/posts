package com.example.postsapplication.ui.postsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.postsapplication.R
import com.example.postsapplication.databinding.PostsFragmentBinding
import com.example.postsapplication.models.PostModel
import com.example.postsapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : BaseFragment<PostsFragmentBinding>() {
    private lateinit var view_: View
    private var isLoadingData: Boolean = false
    private lateinit var layoutManager_: LinearLayoutManager
    private val viewModel by viewModels<PostsViewModel>()
    private lateinit var adapter_: PostsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!this::view_.isInitialized) {
            view_ = setViewModelWithDataBinding(inflater, R.layout.posts_fragment)
            binding.viewModel = viewModel
            setList()
        }



        return view_
    }

    fun setList() {
        getPosts()



        binding.postList.apply {
            layoutManager_ = LinearLayoutManager(context)
            layoutManager = layoutManager_
            adapter_ = PostsAdapter(object : PostsAdapter.Interaction{
                override fun onItemSelected(id: Int) {
                    val bundel=Bundle().apply {
                        putInt("id",id)
                    }
                    navController.navigate(R.id.navigation_posts_details_nav,bundel)
                }
            })
            adapter = adapter_
        }
        binding.postList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = layoutManager_!!.itemCount
                Log.d("ddnndndnd","$isLoadingData ")
                if (!isLoadingData && totalItemCount <= layoutManager_.findLastVisibleItemPosition() + 2) {
                    isLoadingData = true
                    getPosts()
                }
            }
        })


    }

    private fun getPosts() {
        viewModel.getPosts()
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            Log.d("dnndndndndndn", "$it")
            isLoadingData = false
            adapter_.submitList(it)
         })
    }

}
