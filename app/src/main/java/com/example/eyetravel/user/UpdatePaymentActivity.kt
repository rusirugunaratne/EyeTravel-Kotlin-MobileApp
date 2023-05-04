package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import com.example.eyetravel.R
import com.example.eyetravel.models.PaymentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdatePaymentActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_payment)

        dbRef = FirebaseDatabase.getInstance().getReference("Payments")

        etGuideId = findViewById(R.id.txt_book_guideId)
        etGuideName = findViewById(R.id.txt_book_guideName)
        etFname = findViewById(R.id.txt_book_firstName)
        etLName = findViewById(R.id.txt_book_lastName)
        etEmail = findViewById(R.id.txt_book_email)
        etContactNo = findViewById(R.id.txt_book_contactNumber)
        etAddress = findViewById(R.id.txt_book_Address)
        etPlacesToVisit = findViewById(R.id.txt_book_placesToVisit)
        etAmount = findViewById(R.id.txt_book_amount)

        setValuesToViews()

        val paymentId = intent.getStringExtra("paymentId")

        val viewPayment = findViewById<Button>(R.id.btn_proceed_to_pay)
        viewPayment.setOnClickListener{
            updatePayment()
            finish()
            val intent = Intent(this, CardDetailsActivity::class.java)
            intent.putExtra("paymentId", paymentId)
            startActivity(intent)
        }
    }

    private fun setValuesToViews() {
        etGuideId.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("guideId"))
        etGuideName.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("guideName"))
        etFname.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("fName"))
        etLName.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("lName"))
        etEmail.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("email"))
        etContactNo.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("contactNo"))
        etAddress.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("address"))
        etPlacesToVisit.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("placesToVisit"))
        etAmount.text = Editable.Factory.getInstance().newEditable(intent.getStringExtra("amount"))
    }

    private fun updatePayment() {
        val guideId = etGuideId.text.toString()
        val guideName = etGuideName.text.toString()
        val firstName = etFname.text.toString()
        val lastName = etLName.text.toString()
        val email = etEmail.text.toString()
        val contactNumber = etContactNo.text.toString()
        val address = etAddress.text.toString()
        val placesToVisit = etPlacesToVisit.text.toString()
        val amount = etAmount.text.toString().toDouble()
        val paymentId = intent.getStringExtra("paymentId")

        dbRef = FirebaseDatabase.getInstance().getReference("Payments/$paymentId")

        val paymentModel = PaymentModel(paymentId, guideId, guideName, firstName, lastName, email, contactNumber, address, placesToVisit, amount)

        dbRef.setValue(paymentModel)

    }
}