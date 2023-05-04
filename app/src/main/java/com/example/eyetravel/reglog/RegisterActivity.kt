package com.example.eyetravel.reglog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.eyetravel.R
import com.example.eyetravel.admin.AdminActivity
import com.example.eyetravel.models.UserModel
import com.example.eyetravel.user.UserActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var coutry: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var passwordConfirm: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_layout)
        supportActionBar?.hide()

        fName = findViewById(R.id.register_fName)
        lName = findViewById(R.id.register_lName)
        coutry = findViewById(R.id.register_country)
        email = findViewById(R.id.register_email)
        password = findViewById(R.id.register_password)
        passwordConfirm = findViewById(R.id.register_confirmPassword)

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        val signUpButton = findViewById<Button>(R.id.btn_signup)
        signUpButton.setOnClickListener{
            saveUser()

        }
    }

    private fun saveUser(){
        val uFName = fName.text.toString()
        val uLName = lName.text.toString()
        val uCountry = coutry.text.toString()
        val uEmail = email.text.toString()
        val uPassword = password.text.toString()
        val uPasswordConf = passwordConfirm.text.toString()

        if(password.text.toString() != passwordConfirm.text.toString()){
            password.error = "Passwords Not Matching"
            passwordConfirm.error = "Passwords Not Matching"
        }

        val userId = dbRef.push().key!!

        val user = UserModel(userId, uFName, uLName, uCountry, uEmail, uPassword)

        dbRef.child(userId).setValue(user).addOnCompleteListener{
            Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_LONG).show()
            Log.d("appMe", "success")
        }.addOnFailureListener { err->
            Toast.makeText(this, "Error ${err.message} ", Toast.LENGTH_LONG).show()
            Log.d("appMe", "${err.message}")
        }

        val intent = Intent(this, UserActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("userId", userId)
        startActivity(intent)

    }

}