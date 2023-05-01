package com.example.eyetravel.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.models.LocationModel
import com.squareup.picasso.Picasso
import java.net.URL

class AdminLocationAdapter (private val locationsList: ArrayList<LocationModel>): RecyclerView.Adapter<AdminLocationAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.admin_location_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = locationsList[position]
        Picasso.get().load(currentItem.imageUrl).into(holder.image)
        holder.name.text = currentItem.name
        holder.description.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return locationsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.admin_location_imageView)
        val name: TextView = itemView.findViewById(R.id.txt_admin_placeName)
        val description: TextView = itemView.findViewById(R.id.txt_admin_placeDescription)
    }
}