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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roydev.tastytrends.CartItem
import com.roydev.tastytrends.ShopItem

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewStallsMenuAdapter
    private var cartList: MutableList<CartItem> = mutableListOf() // Change to CartItem

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

        // Load cart items
        loadCartItems()

        // Initialize adapter with the correct type
//        adapter = RecyclerViewStallsMenuAdapter(object : OnFavoriteClickListener {
//            override fun onFavoriteClick(item: ShopItem) {
//                // Handle favorite click
//            }
//
//            override fun onCartClick(item: CartItem) {
//                // Handle cart click
//            }
//        }, )

        recyclerView.adapter = adapter
    }

    private fun loadCartItems() {
        val sharedPreferences = getSharedPreferences("Cart", Context.MODE_PRIVATE)
        sharedPreferences.all.forEach { entry ->
            if (entry.key.endsWith("_name")) {
                val itemId = entry.value.toString()
                val itemName = sharedPreferences.getString("${itemId}_name", null)
                val itemImage = sharedPreferences.getString("${itemId}_image", null)
                val priceString = sharedPreferences.getString("${itemId}_price", "0") ?: "0"
                val quantity = sharedPreferences.getInt("${itemId}_quantity", 1) // Assuming you save quantity

                // Ensure price is convertible to a number if needed
                val pricePerItem = priceString.toDoubleOrNull() ?: 0.0

                // Create a CartItem and add to cartList if image is available
                itemImage?.let { image ->
                    cartList.add(CartItem(itemId = itemId, itemName = itemName, itemImage = image, quantity = quantity, pricePerItem = pricePerItem))
                }
            }
        }

        if (cartList.isEmpty()) {
            showEmptyCartMessage()
        } else {
            //adapter.notifyDataSetChanged()
        }
    }

    private fun showEmptyCartMessage() {
        // Here you can use a TextView to display the message
        Toast.makeText(this, "No items in the cart", Toast.LENGTH_SHORT).show()
    }
}
