package com.example.eyetravel.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eyetravel.R
import com.example.eyetravel.models.GuideModel
import com.example.eyetravel.models.LocationModel
import com.google.firebase.database.*

class AdminGuidesActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var guidesList: ArrayList<GuideModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_guides)

        val addGuide = findViewById<Button>(R.id.btn_add_guideMain)
        addGuide.setOnClickListener{
            val intent = Intent(this, AddGuideActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.admin_guides_recycler_view)
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
                    val guideAdapter = AdminGuideAdapter(guidesList)
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
