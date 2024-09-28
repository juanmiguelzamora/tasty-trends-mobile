package com.migsdev.tastytrends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

// Define the interface for favorite click
interface OnFavoriteClickListener {
    fun onFavoriteClick(item: JFC)
    fun onCartClick(item: JFC)
}

class RecyclerViewJfcMenuAdapter(
    private val listener: OnFavoriteClickListener,
    private val jfcList: List<JFC>
) : RecyclerView.Adapter<RecyclerViewJfcMenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_jfc_menu_lists, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = jfcList[position]
        holder.tvmenuName.text = item.title
        holder.tvprice.text = item.price
        holder.ivJfcmenuImg.setImageResource(item.image)
        holder.btn_favorite.setImageResource(R.drawable.favorite_icon)
        holder.tvaddcart.text


        holder.btn_favorite.setOnClickListener {
            listener.onFavoriteClick(item) // Call the interface method
        }

        holder.tvaddcart.setOnClickListener {
            listener.onCartClick(item)
        }

        holder.cardView.setOnClickListener {
            Toast.makeText(holder.itemView.context, item.title, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return jfcList.size
    }

    // ViewHolder class must extend RecyclerView.ViewHolder
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvmenuName: TextView = itemView.findViewById(R.id.tvmenuName)
        val ivJfcmenuImg: ImageView = itemView.findViewById(R.id.ivJfcmenuImg)
        val tvprice: TextView = itemView.findViewById(R.id.tvPrice)
        val btn_favorite: ImageView = itemView.findViewById(R.id.btn_favorite)
        val tvaddcart: TextView = itemView.findViewById(R.id.tvaddcart)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}
