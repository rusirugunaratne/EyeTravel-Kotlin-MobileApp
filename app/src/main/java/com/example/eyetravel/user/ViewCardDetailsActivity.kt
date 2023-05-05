package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import com.example.eyetravel.R

class ViewCardDetailsActivity : AppCompatActivity() {
    private lateinit var etCardNumber: TextView
    private lateinit var etExpiryDate: TextView
    private lateinit var etCode: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_card_details)

        etCardNumber = findViewById(R.id.view_card_number)
        etExpiryDate = findViewById(R.id.view_card_expiry_date)
        etCode = findViewById(R.id.view_card_code)

        setValuesToViews()

        val paymentId = intent.getStringExtra("paymentId")
        val cardId = intent.getStringExtra("cardId")

        val btnEdit = findViewById<Button>(R.id.view_card_edit)
        val btnDelete = findViewById<Button>(R.id.view_card_delete)
        val btnPay = findViewById<Button>(R.id.view_card_pay)

        btnEdit.setOnClickListener{
            finish()
            val intent = Intent(this, UpdateCardDetailsActivity::class.java)
            intent.putExtra("paymentId", paymentId)

            intent.putExtra("cardId", cardId)
            intent.putExtra("cardNumber", etCardNumber.text.toString())
            intent.putExtra("expiryDate", etExpiryDate.text.toString())
            intent.putExtra("code", etCode.text.toString())

            startActivity(intent)
        }

        btnDelete.setOnClickListener{
            finish()
            val intent = Intent(this, CardDetailsActivity::class.java)
            intent.putExtra("paymentId", paymentId)
            startActivity(intent)
        }

        btnPay.setOnClickListener{
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
}