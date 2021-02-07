package com.example.postsapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostModel(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)



data class CommentModel(
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)