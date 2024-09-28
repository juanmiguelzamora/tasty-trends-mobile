package com.migsdev.tastytrends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewStallsAdapter(
    private val activity: HomeActivity,
    private val stallsList: List<Stalls>
) : RecyclerView.Adapter<RecyclerViewStallsAdapter.MyViewHolder>() {

    // Callback for item clicks
    var onItemClick: ((Stalls) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_stall_lists_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val stall = stallsList[position]
        holder.ivStallsImg.setImageResource(stall.image)

        // Set up click listener on the card view
        holder.cardView.setOnClickListener {
            // Show a toast message (optional)
            Toast.makeText(activity, stall.title, Toast.LENGTH_SHORT).show()

            // Trigger the onItemClick listener with the clicked stall
            onItemClick?.invoke(stall)
        }
    }

    override fun getItemCount(): Int {
        return stallsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivStallsImg: ImageView = itemView.findViewById(R.id.ivJfcmenuImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}
