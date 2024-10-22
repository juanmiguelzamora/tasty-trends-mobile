package com.migsdev.tastytrends
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.roydev.tastytrends.CartItem
import com.roydev.tastytrends.ShopItem
import com.squareup.picasso.Picasso


class RecyclerViewStallsMenuAdapter(
    private val listener: OnFavoriteClickListener,
    private val shopItemList: MutableList<ShopItem>?,
    private val cartItemList: MutableList<CartItem>?
) : RecyclerView.Adapter<RecyclerViewStallsMenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_stall_menu_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = shopItemList?.get(position)
        if (item != null) {
            holder.tvmenuName.text = item.itemName
        }
        if (item != null) {
            holder.tvprice.text = item.price.toString()
        }

        // Load image using Glide or Picasso
        if (item != null) {
            Picasso.get()
                .load(item.image)
                .placeholder(R.drawable.egg_silog)
                .into(holder.ivStallmenuImg)
        }

        holder.btn_favorite.setOnClickListener {
            if (item != null) {
                listener.onFavoriteClick(item)
            }
        }

        holder.tvaddcart.setOnClickListener {
            // Create a CartItem with quantity set to 1
            val cartItem = item?.let { it1 ->
                CartItem(
                    itemId = it1.itemId,
                    itemName = item.itemName,
                    itemImage = item.image,
                    quantity = 1,
                    pricePerItem = item.price
                )
            }

            if (cartItem != null) {
                listener.onCartClick(cartItem)
            } // Pass the CartItem to the listener
            if (item != null) {
                Snackbar.make(holder.itemView, "${item.itemName} added to cart", Snackbar.LENGTH_SHORT).show()
            }
        }

        holder.cardView.setOnClickListener {
            if (item != null) {
                Toast.makeText(holder.itemView.context, item.itemName, Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun getItemCount(): Int {
        if(shopItemList != null) {
            return shopItemList.size
        }
        else {
            return 0
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvmenuName: TextView = itemView.findViewById(R.id.tvmenuName)
        val ivStallmenuImg: ImageView = itemView.findViewById(R.id.ivJfcmenuImg)
        val tvprice: TextView = itemView.findViewById(R.id.tvPrice)
        val btn_favorite: ImageView = itemView.findViewById(R.id.btn_favorite)
        val tvaddcart: TextView = itemView.findViewById(R.id.tvaddcart)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

}
