package com.example.eyetravel.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.eyetravel.R
import com.example.eyetravel.models.GuideModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class UserViewGuideActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etName: TextView
    private lateinit var etEmail: TextView
    private lateinit var etPhone: TextView
    private lateinit var etAge: TextView
    private lateinit var etImageUrl: ImageView
    private lateinit var etAdditionalDetails: TextView
    private lateinit var etRating: RatingBar
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_view_guide)

        etName = findViewById(R.id.user_guide_view_guideName)
        etEmail = findViewById(R.id.user_guide_view_guideEmail)
        etPhone = findViewById(R.id.user_guide_view_guidePhoneNumber)
        etAge = findViewById(R.id.user_guide_view_guideAge)
        etImageUrl = findViewById(R.id.user_guide_view_imageView)
        etAdditionalDetails = findViewById(R.id.user_guide_view_guideAdditionalDetails)
        etRating = findViewById(R.id.user_guide_ratingBar)
        dbRef = intent.getStringExtra("guideId")
            ?.let { FirebaseDatabase.getInstance().getReference("Guides").child(it) }!!
        bundle = intent.extras!!

        setValuesToViews()

        etRating.setOnRatingBarChangeListener{_, rating, _ -> updateGuide(rating)}

        val guideId = intent.getStringExtra("guideId")
        val guideName = intent.getStringExtra("name")

        val bookNow = findViewById<Button>(R.id.user_book_guide_main)
        bookNow.setOnClickListener{
            val intent = Intent(this, BookGuideActivity::class.java)
            intent.putExtra("guideId", guideId)
            intent.putExtra("guideName", guideName)
            startActivity(intent)
        }

        val call = findViewById<Button>(R.id.btnCall)
        call.setOnClickListener{
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${intent.getStringExtra("phone")}"))
            startActivity(callIntent)
        }
    }

    private fun setValuesToViews(){
        etName.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("name"))
        etEmail.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("email"))
        etPhone.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("phone"))
        Picasso.get().load(intent.getStringExtra("imageUrl")).into(etImageUrl)
        etAge.text = Editable.Factory.getInstance().newEditable(intent.getIntExtra("age", 0).toString() + " YearsOld")
        etAdditionalDetails.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("additionalDetails"))
        val rt = 0
        etRating.rating = intent.getFloatExtra("rating", rt.toFloat()).toFloat()
    }

    private fun updateGuide(ratingFrom: Float) {
        val guideId = intent.getStringExtra("guideId")
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()
        val age = etAge.text.split(" ")[0].toString().toInt()
        val imageUrl = intent.getStringExtra("imageUrl")
        val additionalDetails = etAdditionalDetails.text.toString()

        val rt = 0
        val rating =( intent.getFloatExtra("rating", rt.toFloat()) + ratingFrom) / 2
        val guide = GuideModel(guideId, name, email, phone, age, imageUrl, additionalDetails, rating.toFloat())

        dbRef.setValue(guide)
    }
}