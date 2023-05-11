package com.example.mp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var Name:EditText
    lateinit var Email:EditText
    lateinit var Age:EditText
    lateinit var btn_Sub:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Name = findViewById(R.id.edt_name)
        Email = findViewById(R.id.edt_email)
        Age = findViewById(R.id.edt_age)
        btn_Sub = findViewById(R.id.btn_submit)

        btn_Sub.setOnClickListener(){
            var name = Name.text.toString().trim()
            var email = Email.text.toString().trim()
            var age = Age.text.toString().trim()
            var time_id = System.currentTimeMillis().toString()

            //progress bar
            var progress = ProgressDialog(this)
            progress.setTitle("Saving you data")
            progress.setMessage("Please wait as we save your data")

            if (name.isEmpty()|| email.isEmpty()||age.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            }else{
                // proceed to upload data to firebase
                var child = FirebaseDatabase.getInstance().reference.child("Registry/"+time_id)
                var user_data = USER(name, email, age, time_id)
                
                //show progress
                progress.show()
                
                //save data
                child.setValue(user_data).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Data uploaded successfully", Toast.LENGTH_SHORT).show()
                        var gotoview = Intent(this, view::class.java)
                        startActivity(gotoview)

                    }else{
                        Toast.makeText(this, "Failed to upload", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}