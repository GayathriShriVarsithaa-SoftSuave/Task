package com.example.task_2.digitCode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task_2.base.BaseViewModel

class DigitcodeViewModel : BaseViewModel() {


    fun conform(n1: String, n2: String, n3: String, n4: String) {
        if (n1.isEmpty() || n2.isEmpty() || n3.isEmpty() || n4.isEmpty()) {
            showmessage("missing")
        } else {
            showmessage("Submitted successfully")
            navigate()
        }

    }

    fun cancel() {
        showmessage("Canceled")
    }


}
