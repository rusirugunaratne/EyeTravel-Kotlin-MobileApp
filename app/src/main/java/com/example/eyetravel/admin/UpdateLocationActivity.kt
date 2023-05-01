package com.example.eyetravel.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import com.example.eyetravel.R
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateLocationActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etName: EditText
    private lateinit var etLocation: EditText
    private lateinit var etDescription: EditText
    private lateinit var etImageUrl: EditText
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_location)

        etName = findViewById(R.id.admin_update_placeName)
        etLocation = findViewById(R.id.admin_update_location)
        etDescription = findViewById(R.id.admin_update_locationDescription)
        etImageUrl = findViewById(R.id.admin_update_locationImageUrl)
        dbRef = intent.getStringExtra("locationId")
            ?.let { FirebaseDatabase.getInstance().getReference("Locations").child(it) }!!
        bundle = intent.extras!!

        setValuesToViews()

        val updateLocation = findViewById<Button>(R.id.btn_admin_updateLocation)
        updateLocation.setOnClickListener{
            updateLocation()
            onBackPressed()
        }
    }

    private fun setValuesToViews(){
        etName.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("name"))
        etLocation.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("location"))
        etDescription.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("description"))
        etImageUrl.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("imageUrl"))

    }

    private fun updateLocation() {
        val locationId = intent.getStringExtra("locationId")
        val name = etName.text.toString()
        val location = etLocation.text.toString()
        val description = etDescription.text.toString()
        val imageUrl = etImageUrl.text.toString()

        val locationInfo = LocationModel(locationId, name, location, description, imageUrl)
        dbRef.setValue(locationInfo)
    }
}