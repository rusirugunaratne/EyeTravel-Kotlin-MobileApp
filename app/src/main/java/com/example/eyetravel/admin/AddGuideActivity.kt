package com.example.eyetravel.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.eyetravel.R
import com.example.eyetravel.models.GuideModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddGuideActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAge: EditText
    private lateinit var etImageUrl: EditText
    private lateinit var etAdditionalDetails: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guide)

        etName = findViewById(R.id.admin_add_guideName)
        etEmail = findViewById(R.id.admin_add_guideEmail)
        etPhone = findViewById(R.id.admin_add_guidePhone)
        etAge = findViewById(R.id.admin_add_guideAge)
        etImageUrl = findViewById(R.id.admin_add_guideImageUrl)
        etAdditionalDetails = findViewById(R.id.admin_add_guideDetails)

        dbRef = FirebaseDatabase.getInstance().getReference("Guides")

        val addGuide = findViewById<Button>(R.id.btn_admin_addGuide)
        addGuide.setOnClickListener{
            saveGuide()
            onBackPressed()
        }
    }

    private fun saveGuide() {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()
        val age = etAge.text.toString().toInt()
        val imageUrl = etImageUrl.text.toString()
        val additionalDetails = etAdditionalDetails.text.toString()

        val guideId = dbRef.push().key!!

        val rate = 0
        val guide = GuideModel(guideId, name, email, phone, age, imageUrl, additionalDetails, rate.toFloat())

        dbRef.child(guideId).setValue(guide).addOnCompleteListener{
            Toast.makeText(this, "Guide Added Successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { err->
            Toast.makeText(this, "Error ${err.message} ", Toast.LENGTH_LONG).show()
        }
    }
}