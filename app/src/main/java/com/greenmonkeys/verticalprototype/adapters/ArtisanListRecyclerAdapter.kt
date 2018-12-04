package com.greenmonkeys.verticalprototype.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.greenmonkeys.verticalprototype.R
import com.greenmonkeys.verticalprototype.model.Artisan

class ArtisanListRecyclerAdapter(private var values: List<Artisan>, private val viewGroup: RecyclerView) :
    RecyclerView.Adapter<ArtisanListRecyclerAdapter.ViewHolder>() {
    override fun getItemCount() = values.size
    private var mClickedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.artisan_recycler_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView?.text = values[position].toString()
        holder.profilePicture?.setImageBitmap(values[position].bitmap)
        holder.itemView.setOnClickListener { mClickedPosition = holder.layoutPosition }
    }

    fun setValues(newValues: List<Artisan>) {
        values = newValues
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView? = null
        var profilePicture: ImageView? = null

        init {
            nameTextView = itemView.findViewById(R.id.artisan_recycler_item_name)
            profilePicture = itemView.findViewById(R.id.artisan_recycler_profile_image)
        }
    }
}