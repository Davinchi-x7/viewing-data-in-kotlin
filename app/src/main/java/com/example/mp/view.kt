package com.example.mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import layout.CustomAdapter
import java.util.ArrayList
import kotlin.collections.ArrayList

class view : AppCompatActivity() {
    lateinit var my_list:ListView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        var users:ArrayList<USER> = ArrayList()
        
        var myadapter = CustomAdapter(applicationContext, users)
        
        //access the table
        
        var my_db = FirebaseDatabase.getInstance().reference.child("Registry")
        my_db.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                //get data and display on array
                users.clear()
                for (snap in snapshot.children){
                    var person = snap.getValue(USER::class.java)
                    users.add(person!!)
                }
                myadapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this, "Failed to display data", Toast.LENGTH_SHORT).show()
            }
        })
        my_list =
    }
}