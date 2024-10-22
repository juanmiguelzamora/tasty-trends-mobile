package com.migsdev.tastytrends

import com.roydev.tastytrends.CartItem
import com.roydev.tastytrends.ShopItem

interface OnFavoriteClickListener {
    fun onFavoriteClick(item: ShopItem)
    fun onCartClick(item: CartItem)
}
