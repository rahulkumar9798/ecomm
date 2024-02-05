package com.example.firebaseecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.firebaseecommerce.databinding.ActivityUserLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserLoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserLoginBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth


        binding.btnLogin.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()

            binding.progBar.visibility = View.VISIBLE

            firebaseAuth
                .signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    Log.d("Success", "Login in successfully...  ${it.user!!.uid}")

                    //user login after admin  Approval Start #################################################

                    val firestore = Firebase.firestore
                    firestore.collection("User")
                        .document(it.user!!.uid).get().addOnSuccessListener { doc ->

                            var status = doc.get("status").toString().toInt()


                            if (status == 0) {
                                //sherePref
                                val pref = getSharedPreferences("login", MODE_PRIVATE)
                                pref.edit().putString("uid", "${it.user!!.uid}").apply()

                                binding.progBar.visibility = View.GONE
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                var statusValue = ""
                                if (status == 1) {
                                    statusValue = "InActive"
                                } else if (status == 2) {
                                    statusValue = "InActive"
                                } else {
                                    statusValue = "Blocked"
                                }

                                Toast.makeText(this, "You're in a $statusValue Status", Toast.LENGTH_SHORT).show()
                            }


                            //user login after admin  Approval Close #################################################


                            // normal login Start$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                            //sherePref
//                    val pref = getSharedPreferences("login", MODE_PRIVATE)
//                    pref.edit().putString("uid", "${it.user!!.uid}").apply()
//
//                    binding.progBar.visibility = View.GONE
//
//                    startActivity(Intent(this, MainActivity::class.java))
//                    finish()
                            // normal login Closet$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

                        }.addOnFailureListener {
                            Log.d("Failure", "Cant' Log-in ${it.message}")
                            it.printStackTrace()
                            binding.progBar.visibility = View.GONE
                        }

                }

        }

        binding.txtSinUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.txtLoginOtp.setOnClickListener {
            startActivity(Intent(this, UserOtpRequestActivity::class.java))
        }

        binding.passreset.setOnClickListener {
            startActivity(Intent(this, ForgotPassActivity::class.java))
        }

    }

}