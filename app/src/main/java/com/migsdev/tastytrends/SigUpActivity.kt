package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val pass = findViewById<EditText>(R.id.signup_password).text.toString()
        val conPass = findViewById<EditText>(R.id.signup_confirmpass).text.toString()
        if(pass == conPass) {
            val password = pass
        }

        val btnsignin = findViewById<TextView>(R.id.btnsignin)

        btnsignin.setOnClickListener {
            val username = findViewById<EditText>(R.id.Signup_username).text.toString()
            val email = findViewById<EditText>(R.id.signup_email).text.toString()


            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}