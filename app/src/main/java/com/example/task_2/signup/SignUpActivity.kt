package com.example.task_2.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task_2.MainActivity
import com.example.task_2.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        val bck = findViewById<TextView>(R.id.back1)
        val signup = findViewById<Button>(R.id.signupbutton)
        bck.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        signup.setOnClickListener {
            val check = findViewById<CheckBox>(R.id.checkbox1)
            val name = findViewById<EditText>(R.id.email_box).text.toString()
            val mail = findViewById<EditText>(R.id.password_box).text.toString()
            val password = findViewById<EditText>(R.id.password_box1).text.toString()
            if (name.isEmpty() || mail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fields should not be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (!(check.isChecked)) {
                Toast.makeText(this, "Check the box", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                Toast.makeText(this, "Signed Up successfully", Toast.LENGTH_LONG).show()
            }
        }
    }
}