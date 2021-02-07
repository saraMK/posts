package com.example.postsapplication.data.repo

import com.example.postsapplication.models.CommentModel
import com.example.postsapplication.models.PostModel

interface DataRepo {

    suspend fun getPostsListFromApi():List<PostModel>
    suspend fun getPostFromApi(id:Int):PostModel
    suspend fun getPostCommentApi(id:Int):List<CommentModel>
   // suspend fun getPostCommentApi2(id:Int):Resource<List<CommentModel>>

    suspend fun getPostsListFromDb(page:Int):List<PostModel>
    suspend fun insertPostsListFromDb(posts:List<PostModel>)
    suspend fun deleteTable()

}