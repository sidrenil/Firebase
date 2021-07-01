package com.sidre.firebase

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class GirisActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)

        val girisEmail: EditText = findViewById(R.id.girisEmail)
        val girisParola: EditText = findViewById(R.id.girisParola)
        val girisYapButon: Button = findViewById(R.id.girisyapbutton)
        val girisYeniUyelik: TextView = findViewById(R.id.girisYeniUyelik)



        girisYapButon.setOnClickListener {
            when {
                TextUtils.isEmpty(girisEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this@GirisActivity, "email gir", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(girisParola.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this@GirisActivity, "parola gir", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email: String = girisEmail.text.toString().trim { it <= ' ' }
                    val password: String = girisParola.text.toString().trim { it <= ' ' }

                    girisYapButon.isEnabled = false

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@GirisActivity,
                                        "basarılı",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    val intent =
                                        Intent(this@GirisActivity, ProfilActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)

                                    startActivity(intent)
                                    finish()

                                } else {
                                    Toast.makeText(
                                        this@GirisActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            })
                }
            }

        }
        girisYeniUyelik.setOnClickListener {
            val intent = Intent(this@GirisActivity, UyeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        }
    }


}