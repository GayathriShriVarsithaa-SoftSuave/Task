package com.example.task_2.adapter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_2.R
import com.example.task_2.ecommerce.ProductAdapter
import com.example.task_2.ecommerce.ProductModel
import com.example.task_2.ecommerce.ProductResponse
import com.example.task_2.ecommerce.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.task_2.base.BaseFragment
import com.example.task_2.ecommerce.ProductDetailFragment
import androidx.navigation.fragment.findNavController
import com.example.task_2.databinding.FragmentEcommerceBinding

class EcommerceFragment : Fragment(R.layout.fragment_ecommerce) {
    private var _binding: FragmentEcommerceBinding? = null
    private val binding get() = _binding!!
    //private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentEcommerceBinding.bind(view)

        binding.ecommerceView.layoutManager =
            LinearLayoutManager(requireContext())

        adapter = ProductAdapter(productList) { productId ->
            openDetailFragment(productId)
        }

        binding.ecommerceView.adapter = adapter

        fetchProductsFromApi()
    }

    private fun fetchProductsFromApi() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()

                productList.clear()
                productList.addAll(response.products)
                adapter.notifyDataSetChanged()


            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun openDetailFragment(productId: Int) {
        val bundle = Bundle()
        bundle.putInt("PRODUCT_ID", productId)

        findNavController().navigate(
            R.id.productDetailFragment,
            bundle
        )
    }

}
