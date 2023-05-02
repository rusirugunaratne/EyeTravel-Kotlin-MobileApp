package com.example.eyetravel.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eyetravel.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class UserViewLocationActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etName: TextView
    private lateinit var etDescription: TextView
    private lateinit var etImageUrl: ImageView
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_view_location)

        etName = findViewById(R.id.user_location_view_locationName)
        etDescription = findViewById(R.id.user_location_view_locationDescription)
        etImageUrl = findViewById(R.id.user_location_view_imageView)
        dbRef = intent.getStringExtra("locationId")
            ?.let { FirebaseDatabase.getInstance().getReference("Locations").child(it) }!!
        bundle = intent.extras!!

        setValuesToViews()

        val getDirections = findViewById<Button>(R.id.btn_get_directions)
        getDirections.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("location")))
            startActivity(browserIntent)
        }
    }

    private fun setValuesToViews(){
        etName.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("name"))
        etDescription.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("description"))
        Picasso.get().load(intent.getStringExtra("imageUrl")).into(etImageUrl)
    }
}