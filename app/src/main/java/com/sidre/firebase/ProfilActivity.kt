package com.sidre.firebase


import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sidre.firebase.databinding.ActivityProfilBinding
import java.lang.StringBuilder


class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val database = FirebaseDatabase.getInstance().reference


        val getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                val adSoyad = snapshot.child("uemail").value
                binding.profilEmail.text = adSoyad.toString()

                val email = snapshot.child("uparola").value
                binding.profilParola.text = email.toString()

                val uyeAdi = snapshot.child("uyeAdi").value
                binding.profilAd.text = uyeAdi.toString()
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addListenerForSingleValueEvent(getdata)

        binding.profilCikisYapButon.setOnClickListener {
            intent = Intent(applicationContext, GirisActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


