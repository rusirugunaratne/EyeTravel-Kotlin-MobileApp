package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.eyetravel.R
import com.example.eyetravel.admin.AdminGuidesActivity
import com.example.eyetravel.admin.AdminLocationsActivity
import com.example.eyetravel.admin.AdminProfileActivity
import com.example.eyetravel.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.user_layout)

        val userId = intent.getStringExtra("userId")

        val locationsButton = findViewById<Button>(R.id.btn_user_locations)
        locationsButton.setOnClickListener{
            val intent = Intent(this, UserLocationsActivity::class.java)
            startActivity(intent)
        }

        val guidesButton = findViewById<Button>(R.id.btn_user_guides)
        guidesButton.setOnClickListener{
            val intent = Intent(this, UserGuidesActivity::class.java)
            startActivity(intent)
        }

        val profileButton = findViewById<Button>(R.id.btn_user_profile)
        profileButton.setOnClickListener{
            val intent = Intent(this, UserProfileActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

//        val profileButton = findViewById<Button>(R.id.btn_user_profile)
//        profileButton.setOnClickListener{
//            val intent = Intent(this, PaymentSuccessfulActivity::class.java)
//            startActivity(intent)
//        }
    }
}