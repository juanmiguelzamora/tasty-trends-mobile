package com.migsdev.tastytrends

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewJfcMenuAdapter
    private var CartList: MutableList<JFC> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btncartBack = findViewById<ImageView>(R.id.btncartBack)
        btncartBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rvCartList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager

        loadFavorites()

        adapter = RecyclerViewJfcMenuAdapter(object : OnFavoriteClickListener {
            override fun onFavoriteClick(item: JFC) {
                TODO("Not yet implemented")
            }

            override fun onCartClick(item: JFC) {
            }
        }, CartList)
        recyclerView.adapter = adapter
    }

    private fun loadFavorites() {
        val sharedPreferences = getSharedPreferences("Favorites", Context.MODE_PRIVATE)


        sharedPreferences.all.forEach { entry ->
            if (entry.key.endsWith("_name")) {
                val name = entry.value as String
                val price = sharedPreferences.getString("${name}_price", "")
                val image = sharedPreferences.getInt("${name}_image", 0)

                if (price != null && image != 0) {
                    CartList.add(JFC(name, price, image))
                }
            }
        }

        if (CartList.isEmpty()) {
            Toast.makeText(this, "No cart added", Toast.LENGTH_SHORT).show()
        }
    }
}