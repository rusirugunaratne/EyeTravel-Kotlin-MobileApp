package com.example.eyetravel.user
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.eyetravel.R

class PaymentSuccessfulActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_successful)




        val downloadReceipt = findViewById<Button>(R.id.btn_download_receipt)
        downloadReceipt.setOnClickListener{
            copyToClipboard()
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://eyetravel-37f14.web.app/"))
            startActivity(browserIntent)
        }

        val donePayment = findViewById<Button>(R.id.btn_done_payment)
        donePayment.setOnClickListener{
            onBackPressed()
        }
    }

    private fun copyToClipboard(){
        val paymentId = intent.getStringExtra("paymentId")
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", paymentId)
        clipboardManager.setPrimaryClip(clipData)
        Log.d("id", paymentId.toString())
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }
}