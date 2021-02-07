package com.example.postsapplication.ui.postsList

import android.util.Log
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.postsapplication.data.repo.DataRepo
import com.example.postsapplication.data.repo.HandleNet
import com.example.postsapplication.models.PostModel
import com.example.postsapplication.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Named


public class PostsViewModel @ViewModelInject constructor(val repo: DataRepo) : BaseViewModel() {
    var isLoadingSwipRefrsh = ObservableField(false)
    var liveData: MutableLiveData<List<PostModel>> = MutableLiveData()
    var page = 0
    var counter = ObservableField<Int>(0)
    private var list: MutableList<PostModel> = ArrayList<PostModel>()

    private fun getUpdatePosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val newList = async { getAllPostsFromApi() }
            newList.await()?.let {
                page = 0
                counter.set(0)
                list.clear()
                liveData.postValue(list)
                insertAllPostsToDb(it)
            }


        }
    }

    private suspend fun getAllPostsFromApi(): List<PostModel>? {
        isLoading.set(true)
        try {
            val response = HandleNet().safeApiCall(this@PostsViewModel) {
                repo.getPostsListFromApi()
            }!!
            return response
        } catch (e: Exception) {
        } finally {
            isLoadingSwipRefrsh.set(false)
        }

        return null
    }

    fun insertAllPostsToDb(posts: List<PostModel>) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repo.deleteTable()
                repo.insertPostsListFromDb(posts)
                getPosts()
            }
        } catch (e: Exception) {

        } finally {
        }
    }

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.set(true)
            try {
                val response = repo.getPostsListFromDb(page)

                if (response != null && !response.isEmpty()) {
                    list.addAll(response)
                    liveData.postValue(list)
                    counter.set(list.size)
                    page++

                } else if (page == 0) {
                    getUpdatePosts()
                } else {
                    // end of list

                }
            } catch (e: Exception) {
            } finally {
                isLoading.set(false)
            }
        }
    }

    fun refreshList() {
        isLoadingSwipRefrsh.set(true)
        getUpdatePosts()
    }


}