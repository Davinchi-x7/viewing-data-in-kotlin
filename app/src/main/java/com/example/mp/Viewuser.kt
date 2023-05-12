package com.example.mp

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Viewuser : AppCompatActivity() {

    lateinit var my_list_view:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        my_list_view=findViewById(R.id.m_list)
        var user:ArrayList<USER> = ArrayList()
        var myadapter=custom_ADAPTER(applicationContext,user)
        var my_db = FirebaseDatabase.getInstance().reference.child("Names")

        //access he table
        my_db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //get data and display in array
                user.clear()
                for (snap in snapshot.children){
                    var person = snap.getValue(USER::class.java)
                    user.add(person!!)
                }

                myadapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to Display Data", Toast.LENGTH_SHORT).show()

            }

        })
        my_list_view.adapter = myadapter
    }
}