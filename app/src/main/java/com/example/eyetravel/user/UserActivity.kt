package com.example.eyetravel.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.eyetravel.R
import com.example.eyetravel.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.user_layout)
    }
}