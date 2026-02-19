package com.example.task_2.ecommerce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task_2.base.BaseViewModel
import kotlinx.coroutines.launch

class EcommerceViewModel : BaseViewModel() {

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _logout = MutableLiveData<Boolean>()
    val logout: LiveData<Boolean> = _logout

    fun fetchProducts() {
        _loading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                _products.value = response.products
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
    fun logout(){
        _logout.value=true
    }
    fun logoutend(){
        _logout.value=false
    }

}
