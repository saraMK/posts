package com.example.postsapplication.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.myapplicationtest.DbQuiresDao
import com.example.postsapplication.data.repo.DataRepoManger
import com.example.postsapplication.models.PostModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.internal.builders.JUnit4Builder
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class DbQuiresDaoTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("Test_DbQuires")
     lateinit var dbQuiresDao: DbQuiresDao
    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun insertListOfRows() =
        runBlockingTest {
            val postModelList = listOf<PostModel>(
                PostModel(1, 1, "title1", "body1"),
                PostModel(2, 2, "title2", "body2"),
                PostModel(3, 3, "title3", "body3"),
                PostModel(4, 4, "title4", "body4")
            )
            dbQuiresDao.insert(postModelList)
            val posts = dbQuiresDao.getAllPosts()
            assertThat(posts).isEqualTo(postModelList)
        }

    @Test
    fun deleteTable() =
        runBlockingTest {
            val postModelList = listOf<PostModel>(
                PostModel(1, 1, "title1", "body1"),
                PostModel(2, 2, "title2", "body2"),
                PostModel(3, 3, "title3", "body3"),
                PostModel(4, 4, "title4", "body4")
            )
            dbQuiresDao.insert(postModelList)
            dbQuiresDao.deleteTable()
            val posts = dbQuiresDao.getAllPosts()
            assertThat(posts).isEmpty()
        }

    @Test
    fun getPagingTable() =
        runBlockingTest {
            val postModelList = listOf<PostModel>(
                PostModel(1, 1, "title1", "body1"),
                PostModel(2, 2, "title2", "body2"),
                PostModel(3, 3, "title3", "body3"),
                PostModel(4, 4, "title4", "body4"),
                PostModel(5, 5, "title5", "body5"),
                PostModel(6, 6, "title6", "body6"),
                PostModel(7, 7, "title7", "body7"),
                PostModel(8, 8, "title8", "body8"),
                PostModel(9, 9, "title9", "body9"),
                PostModel(10, 10, "title10", "body10"),
                PostModel(11, 11, "title11", "body11")
            )
            dbQuiresDao.insert(postModelList)
            val posts = dbQuiresDao.getAllPosts(0)
            assertThat(posts).doesNotContain(PostModel(11, 11, "title11", "body11"))
            assertThat(posts).hasSize(10)
            val posts1 = dbQuiresDao.getAllPosts(1)
            assertThat(posts1).contains(PostModel(11, 11, "title11", "body11"))
            val posts2 = dbQuiresDao.getAllPosts(2)
            assertThat(posts2).isEmpty()

        }


}