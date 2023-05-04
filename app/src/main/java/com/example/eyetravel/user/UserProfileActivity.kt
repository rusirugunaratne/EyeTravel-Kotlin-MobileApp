package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.eyetravel.MainActivity
import com.example.eyetravel.R
import com.example.eyetravel.models.UserModel
import com.google.firebase.database.*

class UserProfileActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var etFname: EditText
    private lateinit var etLname: EditText
    private lateinit var etEmail: EditText
    private lateinit var etCountry: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)



        etFname = findViewById(R.id.profile_fName)
        etLname = findViewById(R.id.profile_lName)
        etEmail = findViewById(R.id.profile_email)
        etCountry = findViewById(R.id.profile_country)
        etPassword = findViewById(R.id.profile_password)

        addValues()

        val logoutButton = findViewById<Button>(R.id.btn_user_logout)
        logoutButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val updateDetailsButton = findViewById<Button>(R.id.btn_user_update_details)
        updateDetailsButton.setOnClickListener {
            updatePayment()

        }
    }

    private fun updatePayment() {

        val fName = etFname.text.toString()
        val lName = etLname.text.toString()
        val email = etEmail.text.toString()
        val country = etCountry.text.toString()
        val password = etPassword.text.toString()
        val userId = intent.getStringExtra("userId")
        dbRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
        val userModel = UserModel(userId, fName, lName, country, email, password)
        dbRef.setValue(userModel)
        Toast.makeText(this, "Details Updated", Toast.LENGTH_LONG).show()
    }

    private fun addValues(){
        val userId = intent.getStringExtra("userId")
        dbRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get the data as an object of your desired type
                val userData = dataSnapshot.getValue(UserModel::class.java)

                // Do something with the retrieved data
                if (userData != null) {
                    etFname.text = Editable.Factory.getInstance().newEditable(userData.fName)
                    etLname.text = Editable.Factory.getInstance().newEditable(userData.lName)
                    etEmail.text = Editable.Factory.getInstance().newEditable(userData.email)
                    etCountry.text = Editable.Factory.getInstance().newEditable(userData.country)
                    etPassword.text = Editable.Factory.getInstance().newEditable(userData.password)

                } else {
                    // Data with the specified id does not exist
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors here
            }
        })
    }
}