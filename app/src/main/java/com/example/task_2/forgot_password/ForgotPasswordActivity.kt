package com.example.task_2.forgot_password

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task_2.R
import com.example.task_2.sign_in.SignInActivity
import com.example.task_2.digitCode.DigitcodeActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)
        val cnt = findViewById<Button>(R.id.continuebtn)
        val bck = findViewById<TextView>(R.id.back2)
        val mailbox = findViewById<EditText>(R.id.email__box)
        cnt.setOnClickListener {
            val mail = mailbox.text.toString().trim()

            if (mail.isEmpty()) {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {

                val intent = Intent(this, DigitcodeActivity::class.java)
                startActivity(intent)
            }
            val intent = Intent(this, DigitcodeActivity::class.java)
            startActivity(intent)
        }
        bck.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}