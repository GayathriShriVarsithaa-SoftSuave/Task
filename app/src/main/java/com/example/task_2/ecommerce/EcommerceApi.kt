package com.example.task_2.ecommerce

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface EcommerceApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse
    //fun getProducts(): Call<ProductResponse>
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductModel

}