package com.example.task_2.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_2.base.BaseViewModel

class SignInViewModel : BaseViewModel() {

//    private val _signInStatus = MutableLiveData<String>()
//    val signInStatus: LiveData<String> = _signInStatus

    private val _forgotStatus= MutableLiveData<Boolean>()
    val forgotStatus: LiveData<Boolean> = _forgotStatus
    fun forgot(){
        _forgotStatus.value=true
    }
    fun signin(mail:String,password:String){
        if(mail.isEmpty()){
            //_signInStatus.value="no email"
            showmessage("no email")

        }
        else if(password.isEmpty())
        {
            //_signInStatus.value="no password"
            showmessage("no password")

        }
        else if(!mail.contains("@"))
        {
            //_signInStatus.value="invalid"
            showmessage("invalid")

        }
        else if(mail.equals("user@gmail.com") && password.equals("userpassword"))
        {
            //_signInStatus.value="Signed in successful"
            showmessage("Signed in successful")

        }
        else{
            //_signInStatus.value="Email or password is incorrect"
            showmessage("Email or password is incorrect")

        }
    }
    fun onForgotNavigationDone() {
        _forgotStatus.value = false
    }

}