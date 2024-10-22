package com.roydev.tastytrends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.migsdev.tastytrends.R

class ShopItemAdapter(
    private val shopItems: List<ShopItem>,
    private val cartList: MutableList<CartItem>,
    private val onItemClick: (ShopItem) -> Unit
) : RecyclerView.Adapter<ShopItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView =
            itemView.findViewById(R.id.ivcartImg) // Change to your image view ID
        val itemName: TextView =
            itemView.findViewById(R.id.tvmenuname) // Change to your name TextView ID
        val itemPrice: TextView =
            itemView.findViewById(R.id.tvmenuPrice) // Change to your price TextView ID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_cart, parent, false) // Change to your item layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopItem = shopItems[position]

        // Bind data to views
        holder.itemName.text = shopItem.itemName // Assuming `name` is a property in ShopItem
        holder.itemPrice.text = "₱${shopItem.price}" // Format price as needed
        // Set the item image using your image loading library (e.g., Glide, Picasso)
        // Example: Glide.with(holder.itemView.context).load(shopItem.image).into(holder.itemImage)

        holder.itemView.setOnClickListener {
            addToCart(shopItem)
            onItemClick(shopItem) // Notify any listener if needed
        }
    }

    override fun getItemCount(): Int {
        return shopItems.size
    }

    private fun addToCart(shopItem: ShopItem) {
        // Create a CartItem for the selected shop item
        val cartItem = CartItem(
            itemId = shopItem.itemId,
            itemName = shopItem.itemName,
            itemImage = shopItem.image,
            quantity = 1, // Start with quantity 1
            pricePerItem = shopItem.price
        )
    }
}

//
