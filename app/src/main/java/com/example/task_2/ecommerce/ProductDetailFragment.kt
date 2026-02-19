package com.example.task_2.ecommerce

//import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
import android.view.View
//import android.view.ViewGroup
//import com.example.task_2.R
//import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
//import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.task_2.R
import com.example.task_2.SessionManager
import com.example.task_2.base.BaseFragment
//import com.example.task_2.databinding.FragmentEcommerceBinding
//import kotlinx.coroutines.launch
//import android.widget.ImageView
import com.example.task_2.databinding.FragmentProductDetailBinding

class ProductDetailFragment :
    BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {
    private val viewModel: ProductDetailViewModel by viewModels()


    override fun setupViews() {
        binding.logOutButton.setOnClickListener {
            val sessionManager = SessionManager(requireContext())
            sessionManager.logout()
            findNavController().navigate(R.id.logged_out_To_Welcome,null,NavOptions.Builder()
                .setPopUpTo(R.id.WelcomeFragment,false)
                .build())
        }
    }

    override fun observeViewModel() {
        viewModel.product.observe(viewLifecycleOwner) { product ->
            binding.textViewTitle.text = product.title
            binding.textViewDescription.text = product.description
//            binding.logOutButton.setOnClickListener {
//                onClick(it.id)
//            }
            binding.textViewOriginalPrice.text = "$${product.originalprice}"
            binding.textViewOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            binding.textViewDiscountPrice.text = "$${product.discountprice}"

            Glide.with(requireContext())
                .load(product.image[0])
                .into(binding.imageViewProduct)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressDetailProgress.visibility = View.VISIBLE
            } else {
                binding.progressDetailProgress.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
//        viewModel.logout.observe(viewLifecycleOwner) {
//            val sessionManager = SessionManager(requireContext())
//            sessionManager.logout()
//            viewModel.logoutend()
//            findNavController().navigate(R.id.logged_out_To_Welcome)
//        }
    }

    override fun onClick(viewId: Int) {
//        when (viewId) {
//            R.id.logOutButton -> {
//                viewModel.logout()
//            }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args:ProductDetailFragmentArgs by navArgs()
        val id=args.productId
        //val productId = arguments?.getInt("PRODUCT_ID") ?: return
        viewModel.fetchProduct(id)
    }
}

