package com.example.eyetravel.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.eyetravel.R
import com.example.eyetravel.models.GuideModel
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateGuideActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAge: EditText
    private lateinit var etImageUrl: EditText
    private lateinit var etAdditionalDetails: EditText
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_guide)

        etName = findViewById(R.id.admin_update_guideName)
        etEmail = findViewById(R.id.admin_update_guideEmail)
        etPhone = findViewById(R.id.admin_update_guidePhone)
        etAge = findViewById(R.id.admin_update_guideAge)
        etImageUrl = findViewById(R.id.admin_update_guideImageUrl)
        etAdditionalDetails = findViewById(R.id.admin_update_guideDetails)

        dbRef = intent.getStringExtra("guideId")
            ?.let { FirebaseDatabase.getInstance().getReference("Guides").child(it) }!!
        bundle = intent.extras!!

        setValuesToViews()

        val updateGuide = findViewById<Button>(R.id.btn_admin_updateGuideMain)
        updateGuide.setOnClickListener{
            updateGuide()
            onBackPressed()
        }
    }

    private fun setValuesToViews(){
        etName.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("name"))
        etEmail.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("email"))
        etPhone.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("phone"))
        etImageUrl.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("imageUrl"))
        etAge.text = Editable.Factory.getInstance().newEditable(intent.getIntExtra("age", 0).toString())
        etAdditionalDetails.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("additionalDetails"))

    }

    private fun updateGuide() {
        val guideId = intent.getStringExtra("guideId")
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()
        val age = etAge.text.toString().toInt()
        val imageUrl = etImageUrl.text.toString()
        val additionalDetails = etAdditionalDetails.text.toString()

        if(name == "" || email == "" || phone == "" || age == null || imageUrl == "" || additionalDetails == ""){
            Toast.makeText(this, "All fields required", Toast.LENGTH_LONG).show()
        }else{
            val rt = 0
            val rating = intent.getFloatExtra("rating", rt.toFloat())
            val guide = GuideModel(guideId, name, email, phone, age, imageUrl, additionalDetails, rating.toFloat())
            dbRef.setValue(guide)
        }
    }
}