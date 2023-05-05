package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.eyetravel.R
import com.example.eyetravel.models.CardModel
import com.example.eyetravel.models.PaymentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CardDetailsActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference

    private lateinit var etCardNumber: EditText
    private lateinit var etExpiryDate: EditText
    private lateinit var etCode: EditText

    private lateinit var cardId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        dbRef = FirebaseDatabase.getInstance().getReference("Cards")

        etCardNumber = findViewById(R.id.add_card_number)
        etExpiryDate = findViewById(R.id.add_card_expiry_date)
        etCode = findViewById(R.id.add_card_code)

        val paymentId = intent.getStringExtra("paymentId")

        val payNow = findViewById<Button>(R.id.btn_pay_now)
        payNow.setOnClickListener{
            saveCard()
            finish()
            val intent = Intent(this, ViewCardDetailsActivity::class.java)
            intent.putExtra("paymentId", paymentId)

            intent.putExtra("cardId", cardId)
            intent.putExtra("cardNumber", etCardNumber.text.toString())
            intent.putExtra("expiryDate", etExpiryDate.text.toString())
            intent.putExtra("code", etCode.text.toString())

            startActivity(intent)
        }
    }

    private fun saveCard(){
        val cardNumber = etCardNumber.text.toString()
        val expiryDate = etExpiryDate.text.toString()
        val code = etCode.text.toString()

        cardId = dbRef.push().key!!

        val cardModel = CardModel(cardId, cardNumber, expiryDate, code)

        dbRef.child(cardId).setValue(cardModel).addOnCompleteListener{
            Toast.makeText(this, "Card Details Saved", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { err->
            Toast.makeText(this, "Error ${err.message} ", Toast.LENGTH_LONG).show()
        }
    }
}