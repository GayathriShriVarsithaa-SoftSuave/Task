package com.example.task_2.forgot_password

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
import com.example.task_2.base.BaseViewModel

class ForgotPasswordViewModel : BaseViewModel() {
    //    private val _msg = MutableLiveData<String>()
//    val msg: LiveData<String> = _msg
//    private val _navigateNext = MutableLiveData<Boolean>()
//    val navigateNext: LiveData<Boolean> = _navigateNext
    fun conti(mail: String) {
        if (mail.isEmpty()) {
            //_msg.value="no mail"
            showmessage("no mail")

        } else if (!mail.contains("@")) {
            //_msg.value="invalid"
            showmessage("invalid")
        } else {
            navigate()
            //_navigateNext.value=true
        }
    }

    fun next() {
        navigationdone()
        //_navigateNext.value=false
    }

}