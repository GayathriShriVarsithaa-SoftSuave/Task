package com.example.task_2.network

import com.example.task_2.model.Post
import com.example.task_2.model.PostRequest
import com.example.task_2.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @POST("posts")
    suspend fun  createPost(
        @Body postRequest: PostRequest
    ): Response<PostResponse>
}