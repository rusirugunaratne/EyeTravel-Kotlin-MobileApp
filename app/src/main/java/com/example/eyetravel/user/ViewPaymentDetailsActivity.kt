package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import com.example.eyetravel.R

class ViewPaymentDetailsActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_view_payment_details)

        etGuideId = findViewById(R.id.lbl_guideId)
        etGuideName = findViewById(R.id.lbl_guideName)
        etFname = findViewById(R.id.lbl_fname)
        etLName = findViewById(R.id.lbl_lname)
        etEmail = findViewById(R.id.lbl_email)
        etContactNo = findViewById(R.id.lbl_contact)
        etAddress = findViewById(R.id.lbl_address)
        etPlacesToVisit = findViewById(R.id.lbl_places)
        etAmount = findViewById(R.id.lbl_amount)

        val paymentId = intent.getStringExtra("paymentId")

        setValuesToViews()

        val updatePayment = findViewById<Button>(R.id.btn_pay_update)
        updatePayment.setOnClickListener{
            finish()
            val intent = Intent(this, UpdatePaymentActivity::class.java)
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

        val payNow = findViewById<Button>(R.id.ptn_pay_pay)
        payNow.setOnClickListener {
            finish()
            val intent = Intent(this, CardDetailsActivity::class.java)
            intent.putExtra("paymentId", paymentId)
            startActivity(intent)
        }

        val delete = findViewById<Button>(R.id.btn_pay_delete)
        delete.setOnClickListener {
            onBackPressed()
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


}