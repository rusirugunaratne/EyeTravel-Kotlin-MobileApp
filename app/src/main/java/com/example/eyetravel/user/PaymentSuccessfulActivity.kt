package com.example.eyetravel.user
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyetravel.R

class PaymentSuccessfulActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_successful)

        val downloadReceipt = findViewById<Button>(R.id.btn_download_receipt)
        downloadReceipt.setOnClickListener{
            generatePdf()
        }

        val donePayment = findViewById<Button>(R.id.btn_done_payment)
        donePayment.setOnClickListener{
            onBackPressed()
        }
    }

    private fun generatePdf () {

    }
}