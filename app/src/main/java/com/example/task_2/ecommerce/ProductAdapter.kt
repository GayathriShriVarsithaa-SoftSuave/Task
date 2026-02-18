package com.example.task_2.ecommerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task_2.R
import com.example.task_2.databinding.ProductNameBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        val binding: ProductNameBinding
    ) : RecyclerView.ViewHolder(binding.root)
    // val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
    // val textViewTitle: TextView = itemView.findViewById(R.id.pro_Name)
//        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
//        val textViewOriginalPrice: TextView = itemView.findViewById(R.id.textViewOriginalPrice)
//        val textViewDiscountPrice: TextView = itemView.findViewById(R.id.textViewDiscountPrice)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductNameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.proName.text = product.title
//        holder.textViewDescription.text = product.description
//        holder.textViewOriginalPrice.text = "$${product.originalprice}"
//        holder.textViewDiscountPrice.text = "$${product.discountprice}"
//
//        holder.textViewOriginalPrice.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG

        Glide.with(holder.binding.imageViewProduct.context)
            .load(product.thumbnail)
            .into(holder.binding.imageViewProduct)
        holder.binding.root.setOnClickListener {
            onItemClick(product.id)
        }
    }

    override fun getItemCount(): Int = productList.size
}
