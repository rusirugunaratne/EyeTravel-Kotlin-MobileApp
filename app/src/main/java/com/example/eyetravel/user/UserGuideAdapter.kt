package com.example.eyetravel.user

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.admin.AdminGuideAdapter
import com.example.eyetravel.admin.UpdateGuideActivity
import com.example.eyetravel.models.GuideModel
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class UserGuideAdapter(private val guidesList: ArrayList<GuideModel>): RecyclerView.Adapter<UserGuideAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_guide_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = guidesList[position]
        Picasso.get().load(currentItem.imageUrl).into(holder.image)
        holder.name.text = currentItem.name
        holder.email.text = currentItem.email
        val rate = currentItem.rating
        if (rate != null) {
            holder.ratingBar.rating = rate.toFloat()
        }

        holder.viewButton.setOnClickListener {v->
            val intent = Intent(v.context, UserViewGuideActivity::class.java)
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

    override fun getItemCount(): Int {
        return guidesList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.user_guide_imageView)
        val name: TextView = itemView.findViewById(R.id.txt_user_guideName)
        val email: TextView = itemView.findViewById(R.id.txt_user_guideEmail)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        val viewButton: Button = itemView.findViewById(R.id.btn_user_guide_view)
    }
}