package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnsignin = findViewById<Button>(R.id.btnsignin)
        btnsignin.setOnClickListener {
            /*
            val apiService = ApiClient.apiService
            val email = findViewById<EditText>(R.id.login_email).text.toString()
            val password = findViewById<EditText>(R.id.login_password).text.toString()


            btnsignin.isEnabled = false // Disable the button
            CoroutineScope(Dispatchers.IO).launch {

                try {
                    val data = apiService.Login(LoginReq(email, password))

                } catch (e: Exception) {
                    // Handle the error
                }
            }


             */
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val btnsignup = findViewById<Button>(R.id.btnreturn)
        btnsignup.setOnClickListener {
            val intent = Intent(this, SigUpActivity::class.java)
            startActivity(intent)
        }

        val tvforgotbtn = findViewById<TextView>(R.id.tvforgotbtn)
        tvforgotbtn.setOnClickListener {
            val intent = Intent(this, ForgotPassActivity::class.java)
            startActivity(intent)
        }
    }
}