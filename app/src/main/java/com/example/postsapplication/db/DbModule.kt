package com.example.postsapplication.db

import android.content.Context
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
object  DbModule {
    @Provides
    @Singleton
      fun getDbQuire(@ApplicationContext context: Context): DbQuiresDao =
        DatabaseManager.getInstance(context).dbQuires()!!
}