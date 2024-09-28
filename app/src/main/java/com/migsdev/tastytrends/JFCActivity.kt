package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JFCActivity : AppCompatActivity(), OnFavoriteClickListener {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewJfcMenuAdapter: RecyclerViewJfcMenuAdapter? = null
    private var jfcList = mutableListOf<JFC>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jfcactivity)

        // Setup edge-to-edge behavior
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btncart = findViewById<ImageView>(R.id.btncart)
        btncart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        val btnback = findViewById<ImageView>(R.id.btnback)
        btnback.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        jfcList = ArrayList()

        recyclerView = findViewById<View>(R.id.rvJfcLists) as RecyclerView
        recyclerViewJfcMenuAdapter = RecyclerViewJfcMenuAdapter(this, jfcList)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewJfcMenuAdapter

        prepareJfcListData()
    }

    private fun prepareJfcListData() {
        var jfcmenu = JFC("Egg Silog", " ₱50.00", R.drawable.egg_silog)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Burger Egg Silog", " ₱50.00", R.drawable.burger_steak)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Rice Chicker", " ₱50.00", R.drawable.rice_chicken)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Java Rice Egg Skinless", " ₱50.00", R.drawable.skinless)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Java Rice Egg Ham", " ₱50.00", R.drawable.ham)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Java Rice Egg Hamonado", " ₱50.00", R.drawable.hamonado)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Java Rice Egg Longganisa", " ₱50.00", R.drawable.longganisa)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Java Rice Egg Hotdog", " ₱50.00", R.drawable.hotdog)
        jfcList.add(jfcmenu)

        jfcmenu = JFC("Chowfan Rice", " ₱50.00", R.drawable.chowfan_siomai)
        jfcList.add(jfcmenu)

        recyclerViewJfcMenuAdapter!!.notifyDataSetChanged()
    }

    // Implementation of the OnFavoriteClickListener interface
    override fun onFavoriteClick(item: JFC) {
        saveToFavorites(item)
    }

    // Save the item to favorites
    private fun saveToFavorites(item: JFC) {
        val sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the item properties in SharedPreferences
        editor.putString("${item.title}_name", item.title)
        editor.putString("${item.title}_price", item.price)
        editor.putInt("${item.title}_image", item.image)

        // Commit the changes
        editor.apply()

        // Optionally show a Toast message to confirm the action
        Toast.makeText(this, "${item.title} added to favorites", Toast.LENGTH_SHORT).show()
    }

    override fun onCartClick(item: JFC) {
        saveToCart(item)
    }

    // Save the item to favorites
    private fun saveToCart(item: JFC) {
        val sharedPreferences = getSharedPreferences("Cart", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the item properties in SharedPreferences
        editor.putString("${item.title}_name", item.title)
        editor.putString("${item.title}_price", item.price)
        editor.putInt("${item.title}_image", item.image)

        // Commit the changes
        editor.apply()

        // Optionally show a Toast message to confirm the action
        Toast.makeText(this, "${item.title} added to cart", Toast.LENGTH_SHORT).show()
    }
}
