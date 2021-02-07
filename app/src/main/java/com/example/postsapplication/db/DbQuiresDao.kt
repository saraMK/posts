package com.example.myapplicationtest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postsapplication.models.PostModel

@Dao
public interface DbQuiresDao {
    @Query("SELECT * FROM PostModel LIMIT :page*10 , 10")
    suspend fun getAllPosts(page:Int): List<PostModel>

    @Query("SELECT * FROM PostModel")
    suspend fun getAllPosts(): List<PostModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rows: List<PostModel>)

    @Query("DELETE FROM PostModel")
    fun deleteTable()
}