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
    }
}