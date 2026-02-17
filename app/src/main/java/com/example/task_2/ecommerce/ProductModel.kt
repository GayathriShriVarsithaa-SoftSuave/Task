package com.example.task_2.ecommerce

import com.google.gson.annotations.SerializedName

data class ProductModel(
    val title: String,
    val description: String,
    val id: Int,
    val thumbnail: String,
    @SerializedName("images") val image: List<String>,
    @SerializedName("price") val originalprice: Double,
    @SerializedName("discountPercentage") val discountprice: Double
)
