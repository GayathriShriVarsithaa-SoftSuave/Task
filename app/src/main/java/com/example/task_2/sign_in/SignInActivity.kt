package com.example.task_2.sign_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task_2.MainActivity
import com.example.task_2.R
import com.example.task_2.forgot_password.ForgotPasswordActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
        val forgotbtn = findViewById<TextView>(R.id.idforgotpassword)
        val bck = findViewById<TextView>(R.id.idback)
        val sig = findViewById<Button>(R.id.idsigninbutton)

        forgotbtn.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        bck.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        sig.setOnClickListener {
            val mailid = findViewById<EditText>(R.id.idemail_box).text.toString()
            val password = findViewById<EditText>(R.id.idpassword_box).text.toString()
            if (mailid.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                if (mailid.equals("user@gmail.com") && password.equals("userpassword")) {
                    Toast.makeText(this, "Signed in", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this,
                        "Mail or password is wrong. Check it again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}