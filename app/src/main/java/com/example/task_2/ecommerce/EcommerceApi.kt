package com.example.task_2.ecommerce

import retrofit2.Call
import retrofit2.http.GET

interface EcommerceApi {
    @GET("products")
    fun getProducts(): Call<ProductResponse>
}