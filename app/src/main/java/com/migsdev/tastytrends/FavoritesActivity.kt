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

class FavoritesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewJfcMenuAdapter
    private var favoriteList: MutableList<JFC> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites)

        // Setup edge-to-edge behavior
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Back button behavior
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
//        recyclerView!!.adapter = FavoritesActivity


        // Load the list of favorites
        loadFavorites()

        // Initialize and set adapter with a dummy listener
        adapter = RecyclerViewJfcMenuAdapter(object : OnFavoriteClickListener {
            override fun onFavoriteClick(item: JFC) {
                // Handle favorite click if needed
            }

            override fun onCartClick(item: JFC) {
                TODO("Not yet implemented")
            }
        }, favoriteList)
        recyclerView.adapter = adapter
    }

    // Function to load favorites from SharedPreferences
    private fun loadFavorites() {
        val sharedPreferences = getSharedPreferences("Favorites", Context.MODE_PRIVATE)

        // Iterate through the shared preferences to retrieve the saved favorites
        sharedPreferences.all.forEach { entry ->
            if (entry.key.endsWith("_name")) {
                val name = entry.value as String
                val price = sharedPreferences.getString("${name}_price", "")
                val image = sharedPreferences.getInt("${name}_image", 0)

                if (price != null && image != 0) {
                    favoriteList.add(JFC(name, price, image))
                }
            }
        }

        // Show message if no favorites are added
        if (favoriteList.isEmpty()) {
            Toast.makeText(this, "No favorites added", Toast.LENGTH_SHORT).show()
        }
    }
}
