package com.example.task_2.ecommerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task_2.R

class ProductAdapter(private val productList: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewOriginalPrice: TextView = itemView.findViewById(R.id.textViewOriginalPrice)
        val textViewDiscountPrice: TextView = itemView.findViewById(R.id.textViewDiscountPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_name, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.textViewTitle.text = product.title
        holder.textViewDescription.text = product.description
        holder.textViewOriginalPrice.text = "₹${product.originalprice}"
        holder.textViewDiscountPrice.text = "₹${product.discountprice}"

        holder.textViewOriginalPrice.paintFlags =android.graphics.Paint.STRIKE_THRU_TEXT_FLAG

        Glide.with(holder.imageViewProduct.context)
            .load(product.image[0])
            .into(holder.imageViewProduct)
    }

    override fun getItemCount(): Int = productList.size
}
