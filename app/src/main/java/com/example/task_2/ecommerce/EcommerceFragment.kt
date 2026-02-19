package com.example.task_2.ecommerce

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.util.Log
import android.view.View
//import android.view.ViewGroup
import android.widget.Toast
//import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
//import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
import com.example.task_2.R
//import com.example.task_2.ecommerce.ProductAdapter
//import com.example.task_2.ecommerce.ProductModel
//import com.example.task_2.ecommerce.ProductResponse
//import com.example.task_2.ecommerce.RetrofitInstance
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import com.example.task_2.base.BaseFragment
//import com.example.task_2.ecommerce.ProductDetailFragment
import androidx.navigation.fragment.findNavController
import com.example.task_2.SessionManager
import com.example.task_2.base.BaseFragment
import com.example.task_2.databinding.FragmentEcommerceBinding
//import com.example.task_2.ecommerce.EcommerceFragmentDirections

//import com.example.task_2.ecommerce.ProductAdapter
//import com.example.task_2.ecommerce.ProductModel
//import com.example.task_2.ecommerce.RetrofitInstance

class EcommerceFragment :
    BaseFragment<FragmentEcommerceBinding>(FragmentEcommerceBinding::inflate) {

    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductModel>()
    private val viewModel: EcommerceViewModel by viewModels()
    override fun setupViews() {
        binding.ecommerceView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(productList) { productId ->
            openDetailFragment(productId)
        }
        binding.logOut.setOnClickListener {
            onClick(it.id)
        }
        binding.ecommerceView.adapter = adapter
        viewModel.fetchProducts()
    }

    override fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            productList.clear()
            productList.addAll(products)
            adapter.notifyDataSetChanged()
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.infiniteProgress.visibility = View.VISIBLE
            } else {
                binding.infiniteProgress.visibility = View.GONE
            }
        }
        viewModel.msg.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        viewModel.logout.observe(viewLifecycleOwner) { shouldlogout ->
            if (shouldlogout) {
                val sessionManager = SessionManager(requireContext())
                sessionManager.logout()
                findNavController().navigate(R.id.ecommerce_to_Welcome
                ,null,NavOptions.Builder()
                        .setPopUpTo(R.id.WelcomeFragment,false)
                        .build())
                viewModel.logoutend()
            }
        }
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            R.id.logOut -> {
                viewModel.logout()
            }
        }
    }

    private fun openDetailFragment(productId: Int) {
//        val bundle = Bundle()
//        bundle.putInt("PRODUCT_ID", productId)
//
//        findNavController().navigate(
//            R.id.productDetailFragment,
//            bundle
//        )
        val action=EcommerceFragmentDirections
            .actionEcommerceToProductDetail(
                productId=productId
            )
        findNavController().navigate(action)
    }
}
