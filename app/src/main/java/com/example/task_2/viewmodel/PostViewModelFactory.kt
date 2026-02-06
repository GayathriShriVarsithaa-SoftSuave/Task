package com.example.task_2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task_2.network.RetrofitInstance
import com.example.task_2.repository.PostRepository

class PostViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {

            val repository = PostRepository(
                RetrofitInstance.api,
                context.applicationContext
            )

            @Suppress("UNCHECKED_CAST")
            return PostViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
