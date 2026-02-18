package com.example.task_2.welcome

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
import com.example.task_2.base.BaseViewModel

class WelcomeViewModel : BaseViewModel() {


    fun onSignInClicked() {
        showmessage("Navigating to Sign In")
        navigate()
    }

    fun onCreateAccountClicked() {
        showmessage("Navigating to Sign Up")
        navigate()
    }
}
