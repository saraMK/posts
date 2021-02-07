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
import kotlinx.coroutines.launch
import javax.inject.Named


public class PostsViewModel @ViewModelInject constructor( val repo: DataRepo) : BaseViewModel() {
    var isLoadingSwipRefrsh = ObservableField(false)
    var liveData: MutableLiveData<List<PostModel>> = MutableLiveData()
    var page = 0
    var counter = ObservableField<Int>(0)
    private var list: MutableList<PostModel> = ArrayList<PostModel>()

    private fun getAllPostsFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.set(true)
            try {
                val response = HandleNet().safeApiCall(this@PostsViewModel) {
                repo.getPostsListFromApi()
            }!!
             response?.let {
                 page = 0
                 counter.set(0)
                 list.clear()
                 liveData.postValue(list)
                insertAllPostsToDb(response)

            }
            }catch (e: Exception){}
            finally {
               // isLoading.set(false)
                isLoadingSwipRefrsh.set(false)
                Log.d("dndndndndn","here")
            }
        }
    }

    fun insertAllPostsToDb(posts: List<PostModel>) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repo.deleteTable()
                repo.insertPostsListFromDb(posts)

                getPosts()
            }
        } catch (e: Exception) {

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
                    Log.d("dndndndndn","first")
                    getAllPostsFromApi()
                    Log.d("dndndndndn","end")
                } else {
                    // end of list

                }
            } catch (e: Exception) {
             } finally {
                isLoading.set(false)
             }
        }
    }

    fun refreshList()  {
        isLoadingSwipRefrsh.set(true)
        getAllPostsFromApi()
    }


}