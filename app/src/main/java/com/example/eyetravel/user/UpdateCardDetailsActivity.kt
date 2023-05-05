package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.eyetravel.R
import com.example.eyetravel.models.CardModel
import com.example.eyetravel.models.PaymentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateCardDetailsActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etCardNumber: EditText
    private lateinit var etExpiryDate: EditText
    private lateinit var etCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card_details)

        etCardNumber = findViewById(R.id.update_card_number)
        etExpiryDate = findViewById(R.id.update_card_expiry_date)
        etCode = findViewById(R.id.update_card_code)

        val paymentId = intent.getStringExtra("paymentId")

        setValuesToViews()

        val btnPay = findViewById<Button>(R.id.update_card_pay)
        btnPay.setOnClickListener{
            updateCard()
            finish()
            val intent = Intent(this, PaymentSuccessfulActivity::class.java)
            intent.putExtra("paymentId", paymentId)
            startActivity(intent)
        }
    }

    private fun setValuesToViews() {
        etCardNumber.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("cardNumber"))
        etExpiryDate.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("expiryDate"))
        etCode.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("code"))
    }

    private fun updateCard() {
        val cardNumber = etCardNumber.text.toString()
        val expiryDate = etExpiryDate.text.toString()
        val code = etCode.text.toString()

        val cardId = intent.getStringExtra("cardId")

        dbRef = FirebaseDatabase.getInstance().getReference("Cards/$cardId")

        val cardModel = CardModel(cardId, cardNumber, expiryDate, code)

        dbRef.setValue(cardModel)

    }
}