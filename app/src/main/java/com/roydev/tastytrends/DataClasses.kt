package com.roydev.tastytrends

data class CartItem(
    val itemId: String,
    val itemName: String?,
    val itemImage: String,
    val quantity: Int,
    val pricePerItem: Double // Adding price for calculation
) {
    fun totalPrice(): Double {
        return quantity * pricePerItem
    }
}


data class ShopItem(
    val itemId: String,     // Unique identifier for the shop item
    val shopId: String,     // Unique identifier for the shop selling the item
    val itemName: String,   // Name of the shop item
    val price: Double,      // Price of the item (considering decimals)
    val image: String,      // URL or resource ID for the item's image
    val available: Boolean,  // Availability status of the item
    // val description: String? // Optional description of the item
)fun ShopItem.formattedPrice(): String {
    return String.format("₱%.2f", price)
}


data class Stalls(
    val shopId: String,         // Unique identifier for the shop (stall)
    val shopName: String,       // Name of the shop (stall)
    val shopOwnerId: String,    // Unique identifier for the shop owner
//    val expenses: Double?,       // Total expenses incurred by the shop
//    val sales: Double?,          // Total sales made by the shop
    val image: String           // URL or resource ID for the shop's image
)
