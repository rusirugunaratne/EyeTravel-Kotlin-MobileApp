package com.example.eyetravel.reglog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.eyetravel.R
import com.example.eyetravel.admin.AdminActivity
import com.example.eyetravel.models.UserModel
import com.example.eyetravel.user.UserActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        etEmail = findViewById(R.id.login_email)
        etPassword = findViewById(R.id.login_password)

        var userFound = false
        var userId = "userId"
        var dataLoading = false

        val login = findViewById<Button>(R.id.btn_login)
        login.setOnClickListener{
            if(etEmail.text.toString() == "admin@et.com" && etPassword.text.toString() == "admin"){
                val intent = Intent(this, AdminActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }else{
                dbRef = FirebaseDatabase.getInstance().getReference("Users")
                dbRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        dataLoading = true
                        if(snapshot.exists()){
                            for(user in snapshot.children){
                                val userData = user.getValue(UserModel::class.java)
                                if (userData != null) {
                                    if (userData.email == etEmail.text.toString() && userData.password == etPassword.text.toString()){
                                        userFound = true
                                        userId = userData.userId.toString()
                                        break
                                    }
                                }
                            }
                            dataLoading = false
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                if(!dataLoading){
                    if(userFound){
                        val intent = Intent(this, UserActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        intent.putExtra("userId", userId)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "No user found", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}