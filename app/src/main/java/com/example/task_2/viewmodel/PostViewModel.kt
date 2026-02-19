package com.example.task_2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_2.model.Post
import com.example.task_2.repository.PostRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPosts()
            _posts.postValue(result)
        }
    }

    fun createPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createPost(post)
        }
    }
}
