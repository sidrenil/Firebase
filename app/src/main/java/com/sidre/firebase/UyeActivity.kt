package com.sidre.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.sidre.firebase.databinding.ActivityUyeBinding

import java.lang.StringBuilder


class UyeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUyeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUyeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = FirebaseDatabase.getInstance().reference



        binding.uyekaydetbutton.setOnClickListener {
            val uyeadsoyad = binding.uyeadsoyad.text.toString()
            val uyeemail = binding.uyeemail.text.toString()
            val uyeparola = binding.uyeparola.text.toString()

            //database.setValue(Uye(uyeadsoyad,uyeemail,uyeparola))
            database.child(uyeadsoyad.toString()).setValue(Uye(uyeemail,uyeparola))
            val intent = Intent(this@UyeActivity,ProfilActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
     }
}