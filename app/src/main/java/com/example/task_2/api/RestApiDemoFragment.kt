package com.example.task_2.api

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.task_2.R
import com.example.task_2.model.Post
import com.example.task_2.model.PostRequest
import com.example.task_2.viewmodel.PostViewModel
import com.example.task_2.viewmodel.PostViewModelFactory
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider

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
            val newPost = Post(
                id = System.currentTimeMillis().toInt(),
                title = "Sample Post Title",
                body = "This post is created from the app",
                userId = 1
            )
            viewModel.createPost(newPost)
            tvResult.text = "Post submitted successfully"
        }
        btnGet.setOnClickListener {
            Log.d("CLICK", "GET clicked")
            viewModel.fetchPosts()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("FRAGMENT", "RestApiDemoFragment resumed")
    }
}
