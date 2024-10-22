package com.roydev.tastytrends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.migsdev.tastytrends.R


class RecyclerViewCartMenuAdapter(
    private val cartItemList: MutableList<CartItem>,
    private val listener: OnCartItemClickListener
) : RecyclerView.Adapter<RecyclerViewCartMenuAdapter.MyViewHolder>() {

    interface OnCartItemClickListener {
        fun onRemoveClick(cartItem: CartItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_cart_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val cartItem = cartItemList[position]
//        holder.tvItemName.text = cartItem.itemId // Or use a method to get the name
//        holder.tvQuantity.text = "Quantity: ${cartItem.quantity}"
//        holder.tvPrice.text = "Price: ${cartItem.pricePerItem * cartItem.quantity}"
//
//        // Remove item button
//        holder.btnRemove.setOnClickListener {
//            listener.onRemoveClick(cartItem)
//        }
    }

    override fun getItemCount(): Int = cartItemList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
//        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
//        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
//        val btnRemove: Button = itemView.findViewById(R.id.btnRemove)
    }
}
