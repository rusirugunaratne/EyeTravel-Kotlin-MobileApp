package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyetravel.R

class CardDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        val paymentId = intent.getStringExtra("paymentId")

        val payNow = findViewById<Button>(R.id.btn_pay_now)
        payNow.setOnClickListener{
            finish()
            val intent = Intent(this, PaymentSuccessfulActivity::class.java)
            intent.putExtra("paymentId", paymentId)
            startActivity(intent)
        }
    }
}