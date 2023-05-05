package com.example.eyetravel.user

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.admin.AdminLocationAdapter
import com.example.eyetravel.admin.UpdateLocationActivity
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class UserLocationAdapter (private var locationsList: ArrayList<LocationModel>): RecyclerView.Adapter<UserLocationAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_location_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = locationsList[position]
        Picasso.get().load(currentItem.imageUrl).into(holder.image)
        holder.name.text = currentItem.name
        holder.description.text = currentItem.description

        holder.viewButton.setOnClickListener {v->
            val intent = Intent(v.context, UserViewLocationActivity::class.java)
            intent.putExtra("locationId", currentItem.locationId)
            intent.putExtra("name", currentItem.name)
            intent.putExtra("location", currentItem.location)
            intent.putExtra("description", currentItem.description)
            intent.putExtra("imageUrl", currentItem.imageUrl)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return locationsList.size
    }

    fun updateList(list: List<LocationModel>) {
        locationsList = list as ArrayList<LocationModel>
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.user_location_imageView)
        val name: TextView = itemView.findViewById(R.id.txt_user_placeName)
        val description: TextView = itemView.findViewById(R.id.txt_user_placeDescription)
        val viewButton: Button = itemView.findViewById(R.id.btn_user_location_view)
    }
}