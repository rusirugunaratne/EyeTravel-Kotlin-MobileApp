package com.example.eyetravel.admin

import android.app.AlertDialog
import android.content.Intent
import android.content.Intent.getIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.models.GuideModel
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class AdminGuideAdapter(private val guidesList: ArrayList<GuideModel>): RecyclerView.Adapter<AdminGuideAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.admin_guide_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = guidesList[position]
        Picasso.get().load(currentItem.imageUrl).into(holder.image)
        holder.name.text = currentItem.name
        holder.email.text = currentItem.email
        holder.deleteButton.setOnClickListener {v->
            currentItem.guideId?.let { it1 -> deleteGuide(it1, v) }
        }

        holder.updateButton.setOnClickListener {v->
            val intent = Intent(v.context, UpdateGuideActivity::class.java)
            intent.putExtra("guideId", currentItem.guideId)
            intent.putExtra("name", currentItem.name)
            intent.putExtra("email", currentItem.email)
            intent.putExtra("additionalDetails", currentItem.additionalDetails)
            intent.putExtra("imageUrl", currentItem.imageUrl)
            intent.putExtra("age", currentItem.age)
            intent.putExtra("phone", currentItem.phoneNumber)
            intent.putExtra("rating", currentItem.rating)
            v.context.startActivity(intent)
        }
    }

    private fun deleteGuide(guideId: String, v : View){
        val builder = AlertDialog.Builder(v.context)
        builder.setMessage("Are you sure you want to delete this item?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                val dbRef = FirebaseDatabase.getInstance().getReference("Guides").child(guideId)
                val mTask = dbRef.removeValue()
            }
            .setNegativeButton("No") { dialog, id ->
                Toast.makeText(v.context, "Not Deleted", Toast.LENGTH_LONG).show()
            }
        val alert = builder.create()
        alert.show()
    }

    override fun getItemCount(): Int {
        return guidesList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.admin_guide_imageView)
        val name: TextView = itemView.findViewById(R.id.txt_admin_guideName)
        val email: TextView = itemView.findViewById(R.id.txt_admin_guideEmail)
        val deleteButton: Button = itemView.findViewById(R.id.btn_admin_guideDelete)
        val updateButton: Button = itemView.findViewById(R.id.btn_admin_guide_update)
    }
}