package com.example.postsapplication.ui.postDetails

import android.util.Log
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapplication.data.repo.DataRepo
import com.example.postsapplication.data.repo.HandleNet
import com.example.postsapplication.models.CommentModel
import com.example.postsapplication.models.PostModel
import com.example.postsapplication.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Named

class PostDetailsViewModel @ViewModelInject constructor( val repo: DataRepo) : BaseViewModel() {
     var postModel: ObservableField<PostModel> = ObservableField()
    var liveData: MutableLiveData<List<CommentModel>> = MutableLiveData()
    fun getPostAndCommentsWithID(id:Int){

             isLoading.set(true)

            getPostWithID(id)
             getPostComments(id)

    }
     fun getPostWithID(id:Int) {

        viewModelScope.launch(Dispatchers.IO) {
             try {
                val response = HandleNet().safeApiCall(this@PostDetailsViewModel) {
                    repo.getPostFromApi(id)
                }!!
                 response?.let {
                     postModel.set(it)
                 }

            }catch (e:Exception){}
            finally {
                 isLoading.set(false)
             }
        }
    }
    fun getPostComments(id:Int) {
         viewModelScope.launch(Dispatchers.IO) {
             try {
                val response = HandleNet().safeApiCall(this@PostDetailsViewModel) {
                    repo.getPostCommentApi(id)
                }!!

                response?.let {
                    liveData.postValue(it)
                }
            }catch (e:Exception){

             }
            finally {
                 isLoading.set(false)
             }


        }
    }
 }
