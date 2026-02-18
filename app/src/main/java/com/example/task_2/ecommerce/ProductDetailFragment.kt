package com.example.task_2.ecommerce

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import com.example.task_2.R
//import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
//import android.widget.ImageView
import com.example.task_2.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("PRODUCT_ID") ?: return

        fetchProductDetails(productId, view)
    }

    private fun fetchProductDetails(id: Int, view: View) {
        lifecycleScope.launch {
            try {
                binding.progressDetailProgress.visibility = View.VISIBLE
                val product = RetrofitInstance.api.getProductById(id)

                binding.textViewTitle.text = product.title
                binding.textViewDescription.text = product.description

                binding.textViewOriginalPrice.text = "$${product.originalprice}"
                binding.textViewOriginalPrice.paintFlags =
                    binding.textViewOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                binding.textViewDiscountPrice.text = "$${product.discountprice}"

                Glide.with(requireContext())
                    .load(product.image[0])
                    .into(binding.imageViewProduct)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressDetailProgress.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

