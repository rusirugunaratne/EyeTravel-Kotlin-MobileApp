package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.admin.AddLocationsActivity
import com.example.eyetravel.admin.AdminLocationAdapter
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.*

class UserLocationsActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var locationsList: ArrayList<LocationModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_locations)

        recyclerView = findViewById(R.id.user_locations_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        locationsList = arrayListOf<LocationModel>()

        getLocations()
    }

    private fun getLocations() {
        recyclerView.visibility = View.GONE
        dbRef = FirebaseDatabase.getInstance().getReference("Locations")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                locationsList.clear()
                if(snapshot.exists()){
                    for(location in snapshot.children){
                        val locationData = location.getValue(LocationModel::class.java)
                        locationsList.add(locationData!!)
                    }
                    val locationAdapter = UserLocationAdapter(locationsList)
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