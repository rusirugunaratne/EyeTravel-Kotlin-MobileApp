package com.example.eyetravel.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.admin.AddGuideActivity
import com.example.eyetravel.admin.AdminGuideAdapter
import com.example.eyetravel.models.GuideModel
import com.google.firebase.database.*

class UserGuidesActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var guidesList: ArrayList<GuideModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_guides)

        recyclerView = findViewById(R.id.user_guides_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        guidesList = arrayListOf<GuideModel>()

        getGuides()
    }

    private fun getGuides() {
        recyclerView.visibility = View.GONE
        dbRef = FirebaseDatabase.getInstance().getReference("Guides")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                guidesList.clear()
                if(snapshot.exists()){
                    for(guide in snapshot.children){
                        val guideData = guide.getValue(GuideModel::class.java)
                        guidesList.add(guideData!!)
                    }
                    val guideAdapter = UserGuideAdapter(guidesList)
                    recyclerView.adapter = guideAdapter

                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}