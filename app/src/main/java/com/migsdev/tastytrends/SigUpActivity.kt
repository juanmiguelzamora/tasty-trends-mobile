package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.roydev.tastytrends.RegisterReq
import com.roydev.tastytrends.RetrofitInstance

class SigUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sig_up)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnsignup = findViewById<AppCompatButton>(R.id.btnregsiter)

        btnsignup.setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.Signup_username).text.toString()
            val email = findViewById<TextInputEditText>(R.id.signup_email).text.toString()
            val password = findViewById<TextInputEditText>(R.id.signup_password).text.toString()
            val conPass = findViewById<TextInputEditText>(R.id.signup_confirmpass).text.toString()

            if (password != conPass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(username.isEmpty() || email.isEmpty() || password.isEmpty() || conPass.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                // Register the user
                registerUser(username, email, password)
            }
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val registerReq = RegisterReq( username, email, password)
                val response = RetrofitInstance.api.register(registerReq)

                Log.d("SigUpActivity", "Request: ${Gson().toJson(registerReq)}")

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // Handle success
                        Toast.makeText(this@SigUpActivity, response.message(), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SigUpActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Log.e("SigUpActivity", "Error code: ${response.code()}") // Log the response code
                        val errorBody = response.errorBody() ?: "Unknown error"
                        Log.e("SigUpActivity", "Error: $errorBody")
                        Toast.makeText(this@SigUpActivity, "Error: $errorBody", Toast.LENGTH_SHORT).show()
                    }

                }
            } catch (e: Exception) {
                Log.e("SigUpActivity", "Error: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SigUpActivity, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
