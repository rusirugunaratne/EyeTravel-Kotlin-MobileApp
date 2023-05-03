package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.eyetravel.R
import com.example.eyetravel.models.PaymentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BookGuideActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etGuideId: TextView
    private lateinit var etGuideName: TextView
    private lateinit var etFname: TextView
    private lateinit var etLName: TextView
    private lateinit var etEmail: TextView
    private lateinit var etContactNo: TextView
    private lateinit var etAddress: TextView
    private lateinit var etPlacesToVisit: TextView
    private lateinit var etAmount: TextView
    private lateinit var paymentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_guide)

        dbRef = FirebaseDatabase.getInstance().getReference("Payments")

        etGuideId = findViewById(R.id.txt_book_guideIdMain)
        etGuideName = findViewById(R.id.txt_book_guideNameMain)
        etFname = findViewById(R.id.txt_book_firstNameMain)
        etLName = findViewById(R.id.txt_book_lastNameMain)
        etEmail = findViewById(R.id.txt_book_emailMain)
        etContactNo = findViewById(R.id.txt_book_contactNumberMain)
        etAddress = findViewById(R.id.txt_book_AddressMain)
        etPlacesToVisit = findViewById(R.id.txt_book_placesToVisitMain)
        etAmount = findViewById(R.id.txt_book_amountMain)

        etGuideId.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("guideId"))
        etGuideName.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("guideName"))


        val viewPayment = findViewById<Button>(R.id.btn_book_guideView)
        viewPayment.setOnClickListener{
            savePayment()
            finish()
            val intent = Intent(this, ViewPaymentDetailsActivity::class.java)
            intent.putExtra("guideId", etGuideId.text.toString())
            intent.putExtra("guideName", etGuideName.text.toString())
            intent.putExtra("fName", etFname.text.toString())
            intent.putExtra("lName", etLName.text.toString())
            intent.putExtra("email", etEmail.text.toString())
            intent.putExtra("contactNo", etContactNo.text.toString())
            intent.putExtra("address", etAddress.text.toString())
            intent.putExtra("placesToVisit", etPlacesToVisit.text.toString())
            intent.putExtra("amount", etAmount.text.toString())
            intent.putExtra("paymentId", paymentId)
            startActivity(intent)
        }
    }

    private fun savePayment(){
        val guideId = etGuideId.text.toString()
        val guideName = etGuideName.text.toString()
        val firstName = etFname.text.toString()
        val lastName = etLName.text.toString()
        val email = etEmail.text.toString()
        val contactNumber = etContactNo.text.toString()
        val address = etAddress.text.toString()
        val placesToVisit = etPlacesToVisit.text.toString()
        val amount = etAmount.text.toString().toDouble()


        paymentId = dbRef.push().key!!

        val paymentModel = PaymentModel(paymentId, guideId, guideName, firstName, lastName, email, contactNumber, address, placesToVisit, amount)

        dbRef.child(paymentId).setValue(paymentModel).addOnCompleteListener{
            Toast.makeText(this, "Payment Details Saved", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { err->
            Toast.makeText(this, "Error ${err.message} ", Toast.LENGTH_LONG).show()
        }
    }
}