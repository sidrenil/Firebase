package com.sidre.firebase


import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sidre.firebase.databinding.ActivityProfilBinding
import org.w3c.dom.Comment
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

                val UsEmail = intent.extras?.get("email_id")

                snapshot.children.forEach { uid ->
                    if (uid.child("uyeEmail").value == UsEmail) {

                        val email = uid.child("uyeEmail").value
                        binding.profilEmail.text = email.toString()

                        val parola = uid.child("uyeParola").value
                        binding.profilParola.text = parola.toString()


                        val uyeAdi = uid.child("uyeAdi").value
                        binding.profilAd.text = uyeAdi.toString()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }

        database.addValueEventListener(getdata)

        binding.profilCikisYapButon.setOnClickListener {
            intent = Intent(applicationContext, GirisActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}