package com.example.task_2.linear

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.task_2.R

class LinearListFragment : Fragment(R.layout.fragment_linear_list) {

    private val names = listOf(
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack",
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack",
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack",
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack",
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack",
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack",
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack",
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Helen", "Ian", "Jack"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container = view.findViewById<LinearLayout>(R.id.linearContainer)

        for (name in names) {
            val itemView = layoutInflater.inflate(R.layout.item_name, container, false)
            val textView = itemView.findViewById<TextView>(R.id.textViewName)
            val imageView = itemView.findViewById<ImageView>(R.id.imageviewname)
            textView.text = name
            Glide.with(imageView.context)
                .load("https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp")
                .into(imageView)
            container.addView(itemView)
        }
    }
}
