package com.example.postsapplication.data.repo

import com.example.myapplicationtest.DbQuiresDao
 import com.example.postsapplication.models.PostModel
import com.example.postsapplication.network.NetworkApis
import javax.inject.Inject
import javax.inject.Named

class DataRepoManger @Inject constructor( val dbQuires: DbQuiresDao,  val apis: NetworkApis) : DataRepo {


    override suspend fun getPostsListFromApi() = apis.getPostsList()

    override suspend fun getPostFromApi(id: Int) = apis.getPost(id)

    override suspend fun getPostCommentApi(id: Int) = apis.getCommentsList(id)

    override suspend fun getPostsListFromDb(page: Int) = dbQuires.getAllPosts(page)

    override suspend fun insertPostsListFromDb(posts: List<PostModel>) =dbQuires.insert(posts)
    override suspend fun deleteTable() =dbQuires.deleteTable()
}