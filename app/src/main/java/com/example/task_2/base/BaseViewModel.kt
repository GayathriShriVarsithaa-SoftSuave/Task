package com.example.task_2.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _msg = MutableLiveData<String>()
    val msg: LiveData<String> get() = _msg

    protected val _navi=MutableLiveData<Boolean>()
    val navi: LiveData<Boolean> get() = _navi

    protected fun showmessage(message: String){
        _msg.value=message
    }
    protected fun navigate(){
        _navi.value=true
    }
    fun navigationdone(){
        _navi.value=false
    }
}