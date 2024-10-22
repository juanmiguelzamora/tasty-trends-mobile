package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roydev.tastytrends.CartItem
import com.roydev.tastytrends.RetrofitInstance
import com.roydev.tastytrends.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class StallActivity : AppCompatActivity(), OnFavoriteClickListener {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewStallMenuAdapter: RecyclerViewStallsMenuAdapter? = null
    private var shopItemList = mutableListOf<ShopItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stallactivity)

        // Setup edge-to-edge behavior
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCart = findViewById<ImageView>(R.id.btncart)
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        val btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rvStallLists)
        recyclerViewStallMenuAdapter = RecyclerViewStallsMenuAdapter(this, shopItemList, cartItemList = null)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewStallMenuAdapter

        loadSampleItemData()
        loadStallItemsData()

    }

    private fun loadSampleItemData() {
        // Create sample items
        val sampleItems = listOf(
            ShopItem("1", "jfc_12345678901234567890123456789012", "EggSilog", 50.00, "https://gluttodigest.com/wp-content/uploads/2018/03/beef-tapsilog-silog-what-is-how-to-make-and-where-find-buy-order-best-longsilog-bistro-express-tosilog-bangsilog-spamsilog-menu-recipe-meals-restaurants-near-me-500x363.jpg", true),
        )

        // Clear the list and add sample items
        shopItemList.clear()
        shopItemList.addAll(sampleItems)

        // Notify the adapter about data changes
        recyclerViewStallMenuAdapter!!.notifyDataSetChanged()
    }

    private fun loadStallItemsData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getShopItems("")
                if (response.isSuccessful) {
                    response.body()?.let { items ->
                        shopItemList.clear()
                        shopItemList.addAll(items)
                        withContext(Dispatchers.Main) {
                            recyclerViewStallMenuAdapter!!.notifyDataSetChanged()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@StallActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@StallActivity, "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@StallActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Implementation of the OnFavoriteClickListener interface
    override fun onFavoriteClick(item: ShopItem) {
        saveToFavorites(item)
    }

    // Save the item to favorites
    private fun saveToFavorites(item: ShopItem) {
        val sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the item properties in SharedPreferences
        editor.putString("${item.itemName}_name", item.itemName)
        editor.putString("${item.itemName}_price", item.price.toString())
        editor.putString("${item.itemName}_image", item.image)

        // Commit the changes
        editor.apply()

        // Optionally show a Toast message to confirm the action
        Toast.makeText(this, "${item.itemName} added to favorites", Toast.LENGTH_SHORT).show()
    }

    override fun onCartClick(item: CartItem) {
        saveToCart(item)
    }

    // Save the item to cart
    private fun saveToCart(item: CartItem) {
        val sharedPreferences = getSharedPreferences("Cart", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the item properties in SharedPreferences
        editor.putString("${item.itemName}_name", item.itemName)
        editor.putString("${item.itemName}_price", item.pricePerItem.toString())
        editor.putString("${item.itemId}_image", item.itemImage).apply()

        // Commit the changes
        editor.apply()

        // Optionally show a Toast message to confirm the action
        Toast.makeText(this, "${item.itemName} added to cart", Toast.LENGTH_SHORT).show()
    }
}
