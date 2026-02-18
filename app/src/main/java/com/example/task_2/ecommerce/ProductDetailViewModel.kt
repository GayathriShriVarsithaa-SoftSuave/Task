package com.example.task_2.ecommerce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task_2.base.BaseViewModel
import kotlinx.coroutines.launch

class ProductDetailViewModel : BaseViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _product = MutableLiveData<ProductModel>()
    val product: LiveData<ProductModel> = _product
    private val _logout = MutableLiveData<Boolean>()
    val logout: LiveData<Boolean> = _logout
    fun fetchProduct(productId: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductById(productId)
                _product.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _loading.value = false
            }
        }
    }

    fun logout() {
        _logout.value = true
    }

    fun logoutend() {
        _logout.value = false
    }

}