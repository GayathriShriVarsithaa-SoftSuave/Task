package com.example.task_2.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_2.base.BaseViewModel

class SignUpViewModel : BaseViewModel() {

//    private val _signUpStatus = MutableLiveData<String>()
//    val signUpStatus: LiveData<String> = _signUpStatus

    fun signUp(
        name: String,
        email: String,
        password: String,
        isChecked: Boolean
    ) {
        if(name.isEmpty())
        {
//            _signUpStatus.value="no name"
            showmessage("no name")
        }
        else if(email.isEmpty()){
            //_signUpStatus.value="no mail"
            showmessage("no mail")
        }
        else if(password.isEmpty()){
            //_signUpStatus.value="no password"
            showmessage("no password")
        }
        else if(!isChecked)
        {
            //_signUpStatus.value="no check"
            showmessage("no check")
        }
        else if(!email.contains("@"))
        {
            //_signUpStatus.value="invalid mail"
            showmessage("invalid mail")
        }
        else if(password.length<5){
            //_signUpStatus.value="short"
            showmessage("short")
        }
        else{
            //_signUpStatus.value="Signed up successfully!!"
            showmessage("Signed up sucessfully!!")
        }

    }
}