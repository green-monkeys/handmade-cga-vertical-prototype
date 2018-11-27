package com.greenmonkeys.verticalprototype.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jtorres.handmadeverticalprototype.R
import com.greenmonkeys.verticalprototype.model.Artisan

class ArtisanListRecyclerAdapter(private val values: List<Artisan>): RecyclerView.Adapter<ArtisanListRecyclerAdapter.ViewHolder>() {
    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.artisan_recycler_item_view, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView?.text = values[position].toString()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null
        init {
            textView = itemView.findViewById(R.id.artisan_recycler_item_name)
        }
    }
}