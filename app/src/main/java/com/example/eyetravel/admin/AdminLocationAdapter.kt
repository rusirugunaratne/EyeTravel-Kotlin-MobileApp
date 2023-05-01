package com.example.eyetravel.admin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
        holder.deleteButton.setOnClickListener {
            currentItem.locationId?.let { it1 -> deleteLocation(it1) }
        }

        holder.updateButton.setOnClickListener {v->
            val intent = Intent(v.context, UpdateLocationActivity::class.java)
            intent.putExtra("locationId", currentItem.locationId)
            intent.putExtra("name", currentItem.name)
            intent.putExtra("location", currentItem.location)
            intent.putExtra("description", currentItem.description)
            intent.putExtra("imageUrl", currentItem.imageUrl)
            v.context.startActivity(intent)

        }
    }

    private fun deleteLocation(locationId: String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Locations").child(locationId)
        val mTask = dbRef.removeValue()
    }

    override fun getItemCount(): Int {
        return locationsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.admin_location_imageView)
        val name: TextView = itemView.findViewById(R.id.txt_admin_placeName)
        val description: TextView = itemView.findViewById(R.id.txt_admin_placeDescription)
        val deleteButton: Button = itemView.findViewById(R.id.btn_admin_locationDelete)
        val updateButton: Button = itemView.findViewById(R.id.btn_admin_location_update)
    }
}