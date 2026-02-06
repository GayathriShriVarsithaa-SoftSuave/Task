package com.example.task_2.repository

import android.content.Context
import com.example.task_2.database.DatabaseProvider
import com.example.task_2.model.Post
import com.example.task_2.network.ApiService
import com.example.task_2.utils.NetworkUtils
//decides where to get the database from (API or local database)
class PostRepository(
    private val apiService: ApiService,
    private val context: Context
) {

    private val postDao = DatabaseProvider
        .getDatabase(context)
        .postDao()

    suspend fun getPosts(): List<Post> {
        return if (NetworkUtils.isInternetAvailable(context)) {
            val response = apiService.getPosts()
            val posts = response.body() ?: emptyList()
            postDao.insertPosts(posts)   // save to Room
            posts
        } else {
            postDao.getAllPosts()        // load from Room
        }
    }
}
