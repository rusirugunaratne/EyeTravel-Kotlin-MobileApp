package com.example.eyetravel.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyetravel.R

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.admin_layout)

        val locationsButton = findViewById<Button>(R.id.btn_admin_locations)
        locationsButton.setOnClickListener{
            val intent = Intent(this, AdminLocationsActivity::class.java)
            startActivity(intent)
        }

        val guidesButton = findViewById<Button>(R.id.btn_admin_guides)
        guidesButton.setOnClickListener{
            val intent = Intent(this, AdminGuidesActivity::class.java)
            startActivity(intent)
        }

        val profileButton = findViewById<Button>(R.id.btn_admin_profile)
        profileButton.setOnClickListener{
            val intent = Intent(this, AdminProfileActivity::class.java)
            startActivity(intent)
        }
    }
}