package com.example.postsapplication.db

import android.content.Context
import androidx.room.Room
import com.example.myapplicationtest.DatabaseManager
import com.example.myapplicationtest.DbQuiresDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModuleTest {

    @Provides
    @Named("Test_db")
    fun dbWithMemori(@ApplicationContext context: Context): DatabaseManager =
        Room.inMemoryDatabaseBuilder(context,DatabaseManager::class.java).allowMainThreadQueries().build()

    @Provides
    @Named("Test_DbQuires")
    fun getDbQuireTest(@Named("Test_db") dbManger:DatabaseManager): DbQuiresDao =
        dbManger.dbQuires()!!
}