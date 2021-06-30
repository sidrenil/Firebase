package com.sidre.firebase


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
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


        val getdata = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {


                val adsoyad = snapshot.child("uemail")
                binding.profiladsoyad.text= adsoyad.toString()

                val email = snapshot.child("uparola")
                binding.profilemail.text = email.toString()


                /*
                val adsoyad = snapshot.children.
                val email = snapshot.children

                adsoyad.forEach {
                    binding.profiladsoyad.text = it.toString()
                }
                email.forEach{
                    binding.profilemail.text =it.toString()
                }
            }

          */
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addListenerForSingleValueEvent(getdata)





        binding.profilcikisyapbutton.setOnClickListener {
            intent = Intent(applicationContext, GirisActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


