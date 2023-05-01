package com.example.eyetravel.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.eyetravel.R
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddLocationsActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etName: EditText
    private lateinit var etLocation: EditText
    private lateinit var etDescription: EditText
    private lateinit var etImageUrl: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_locations)

        etName = findViewById(R.id.admin_add_placeName)
        etLocation = findViewById(R.id.admin_add_location)
        etDescription = findViewById(R.id.admin_add_locaitonDescription)
        etImageUrl = findViewById(R.id.admin_add_locationImageUrl)

        dbRef = FirebaseDatabase.getInstance().getReference("Locations")

        val saveLocation = findViewById<Button>(R.id.btn_admin_addLocation)
        saveLocation.setOnClickListener{
            Log.d("appMe", "btn clicked")
            saveLocation()
            onBackPressed()
        }

    }

    private fun saveLocation(){
        val name = etName.text.toString()
        val location = etLocation.text.toString()
        val description = etDescription.text.toString()
        val imageUrl = etImageUrl.text.toString()

        val locationId = dbRef.push().key!!

        val locationModel = LocationModel(locationId,name,location,description,imageUrl)

        dbRef.child(locationId).setValue(locationModel).addOnCompleteListener{
            Toast.makeText(this, "Location Added Successfully", Toast.LENGTH_LONG).show()
            Log.d("appMe", "success")
        }.addOnFailureListener { err->
            Toast.makeText(this, "Error ${err.message} ", Toast.LENGTH_LONG).show()
            Log.d("appMe", "${err.message}")
        }
    }
}