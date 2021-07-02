package com.sidre.firebase


import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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


                val uyeAdi = snapshot.child("uyeAdi").value
                binding.profilAd.text = uyeAdi.toString()

                val email = snapshot.child("uyeEmail").value
                binding.profilEmail.text = email.toString()

                val parola = snapshot.child("uyeParola").value
                binding.profilParola.text = parola.toString()

            }


            override fun onCancelled(error: DatabaseError) {
            }

        }


        binding.profilCikisYapButon.setOnClickListener {
            intent = Intent(applicationContext, GirisActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


