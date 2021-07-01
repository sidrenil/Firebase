package com.sidre.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sidre.firebase.databinding.ActivityUyeBinding

class UyeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUyeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityUyeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = FirebaseDatabase.getInstance().reference

        FirebaseAuth.getInstance()

        binding.uyeKaydetButon.setOnClickListener {
            val uyeAdSoyad = binding.uyeAdSoyad.text.toString()
            val uyeEmail = binding.uyeEmail.text.toString()
            val uyeParola = binding.uyeParola.text.toString()



            if (uyeAdSoyad.isEmpty() || uyeEmail.isEmpty() || uyeParola.isEmpty()) {
                Toast.makeText(this, "bos birakilmaz", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(uyeEmail, uyeParola)
                .addOnCompleteListener {
                    if (it.isSuccessful)
                        return@addOnCompleteListener
                }
            database.setValue(Uye(uyeAdSoyad, uyeEmail, uyeParola))

            val intent = Intent(this@UyeActivity, ProfilActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}