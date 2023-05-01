package com.example.eyetravel.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.*

class AdminLocationsActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var locationsList: ArrayList<LocationModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_locations)
        recyclerView = findViewById(R.id.admin_locations_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        locationsList = arrayListOf<LocationModel>()

        getLocations()

        val signUpButton = findViewById<Button>(R.id.btn_add_location)
        signUpButton.setOnClickListener{
            val intent = Intent(this, AddLocationsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getLocations() {
        recyclerView.visibility = View.GONE
        dbRef = FirebaseDatabase.getInstance().getReference("Locations")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                locationsList.clear()
                if(snapshot.exists()){
                    for(location in snapshot.children){
                        val locationData = location.getValue(LocationModel::class.java)
                        locationsList.add(locationData!!)
                    }
                    val locationAdapter = AdminLocationAdapter(locationsList)
                    recyclerView.adapter = locationAdapter

                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}