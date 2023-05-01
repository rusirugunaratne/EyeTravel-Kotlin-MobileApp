package com.example.eyetravel.admin

import android.content.Intent
import android.content.Intent.getIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
            currentItem.guideId?.let { it1 -> deleteGuide(it1) }
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
            v.context.startActivity(intent)
        }
    }

    private fun deleteGuide(guideId: String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Guides").child(guideId)
        val mTask = dbRef.removeValue()
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