package com.example.firebaseecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.firebaseecommerce.databinding.ActivitySplashBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    val firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)





        Handler().postDelayed(object : Runnable{
            override fun run() {
                var prefs = getSharedPreferences("login", MODE_PRIVATE)
                var uid =  prefs.getString("uid","")

                if(uid !="" ){
                    firestore.collection("User")
                        .document(uid!!)
                        .get()
                        .addOnSuccessListener {
                            var status = it.get("status").toString().toInt()
                            Log.d("seccess", "UserAdded ${it}!!")
//                            dialogAdd.dismiss()

                            if (status == 0) {
                                //binding.progBar.visibility = View.GONE
                                startActivity(Intent(this@SplashActivity, MainActivity::class.java).putExtra("uid", uid))

                                finish()

                            } else {

                               // dialogAdd.dismiss()
                                // binding.progBar.visibility = View.GONE
                                var statusValue = ""
                                if (status == 1) {
                                    statusValue = "InActive"
                                } else if (status == 2) {

                                    statusValue = "Approval Pendding"
                                } else {
                                    statusValue = "Blocked"
                                }

                                Toast.makeText(this@SplashActivity, "You're in a $statusValue", Toast.LENGTH_SHORT).show()
//                                firebaseAuth.signOut()
                                finish()
                            }




                        }

                        .addOnFailureListener {
                            Log.d("User Add Failure", "UserAdded ${it.message}!!")
                            it.printStackTrace()
                        }

                }else{
                    startActivity(Intent(this@SplashActivity, UserLoginActivity::class.java))



                }



            }

        }, 4000)



    }
}