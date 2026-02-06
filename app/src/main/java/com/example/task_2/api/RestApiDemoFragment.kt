package com.example.task_2.api

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.task_2.R
import com.example.task_2.viewmodel.PostViewModel
import com.example.task_2.viewmodel.PostViewModelFactory
import kotlinx.coroutines.launch

class RestApiDemoFragment : Fragment(R.layout.fragment_rest_api_demo) {

    private val viewModel: PostViewModel by viewModels {
        PostViewModelFactory(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGet = view.findViewById<Button>(R.id.btnGet)
        val btnPost = view.findViewById<Button>(R.id.btnPost)
        val tvResult = view.findViewById<TextView>(R.id.tvResult)


        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            if (posts.isNotEmpty()) {
                tvResult.text = posts.first().title
            } else {
                tvResult.text = "No data available"
            }
        }

        btnPost.setOnClickListener {
            tvResult.text = "POST API is demo-only in jsonplaceholder"
        }
        btnGet.setOnClickListener {
            viewModel.fetchPosts()
        }
    }
}
