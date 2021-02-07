package com.example.postsapplication.data.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRepoModule {
    @Singleton
    @Provides

    fun getRepo(repo: DataRepoManger): DataRepo = repo
}