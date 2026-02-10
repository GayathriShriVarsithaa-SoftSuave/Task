package com.example.task_2.adapter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_2.R
import com.example.task_2.ecommerce.ProductAdapter
import com.example.task_2.ecommerce.ProductModel
import com.example.task_2.ecommerce.ProductResponse
import com.example.task_2.ecommerce.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EcommerceFragment : Fragment(R.layout.fragment_ecommerce) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.ecommerceview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter

        fetchProductsFromApi()
    }

    private fun fetchProductsFromApi() {
        RetrofitInstance.api.getProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.products?.let { products ->
                        productList.clear()
                        productList.addAll(products)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), "API Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
