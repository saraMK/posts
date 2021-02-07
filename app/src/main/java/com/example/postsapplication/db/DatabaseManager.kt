package com.example.myapplicationtest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.postsapplication.models.PostModel


@Database(entities = [PostModel::class], version = 1)
public abstract class DatabaseManager : RoomDatabase() {

    abstract fun dbQuires(): DbQuiresDao?
    companion object {

        var instance: DatabaseManager?=null

        fun getInstance(context:Context): DatabaseManager {
            if (instance == null)
            {
                instance=Room.databaseBuilder(context.applicationContext,DatabaseManager::class.java,"postsDb").
                    fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }

}