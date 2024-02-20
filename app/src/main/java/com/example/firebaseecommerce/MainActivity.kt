package com.example.firebaseecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.firebaseecommerce.cart.BottomCartFragment
import com.example.firebaseecommerce.BottomNavigation.FavouriteFragment
import com.example.firebaseecommerce.BottomNavigation.homeFragment.HomeFragment
import com.example.firebaseecommerce.BottomNavigation.ProfileFragment
import com.example.firebaseecommerce.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var userName = ""
    var userIduser = ""
   // var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.root.findViewById(R.id.myToolbar))

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.root.findViewById(R.id.myToolbar),
            R.string.openDrawer,
            R.string.closeDrawer

        )

        //userId = intent.getStringExtra("uid")!!

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


        binding.drawerNavigationView.setNavigationItemSelectedListener {

            val itemId = it.itemId

            when (itemId) {
                R.id.drawer_home->{
                    Toast.makeText(this, "Home", Toast.LENGTH_LONG).show()

                }
                R.id.logoutDrawer->{
                    val logoutDialog = AlertDialog.Builder(this)
                    logoutDialog.setCancelable(false)  //not cancel nahi kya ja sakta

                    logoutDialog.setTitle("Exit")
                    logoutDialog.setMessage("are you sure Logout ${userName}")
                    logoutDialog.setIcon(android.R.drawable.gallery_thumb)
                    logoutDialog.setPositiveButton("Yes"
                    ) { p0, p1 -> super.onBackPressedDispatcher.onBackPressed()

                        val pref = getSharedPreferences("login", MODE_PRIVATE)
                        pref.edit().putString("uid", "").apply()
                        replaceFragment(HomeFragment())
                        startActivity(Intent(this@MainActivity, UserLoginActivity::class.java))
                        Toast.makeText(this@MainActivity, "logout", Toast.LENGTH_LONG).show()
                        finishAffinity()

                    }

                    logoutDialog.setNegativeButton("No"
                    ) { p0, p1 -> }

                    logoutDialog.show()


                }

                else -> {
                    false
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }




        // get pref
        //var prefs = getSharedPreferences("login", MODE_PRIVATE)
        //var uid =  prefs.getString("uid","")






        //Fatch Image In Fire Base and Set Profile Image in User Profile Start.....
//        val userId = FirebaseAuth.getInstance().currentUser!!.uid
//            Log.d("useridCheak", "$userId")
        val firestore = Firebase.firestore
        firestore
            .collection("User")
            .document(getuserId())
            .get()
            .addOnSuccessListener {
                val imgpath = it.get("profilePic").toString()
                userName = it.get("name").toString()
                val email = it.get("email").toString()
                Glide.with(this).load(imgpath).into(binding.root.findViewById(R.id.userProImg))
                binding.root.findViewById<TextView>(R.id.userEmail).text = email
                binding.root.findViewById<TextView>(R.id.userName).text = userName

            }
        //Fatch Image In Fire Base and Set Profile Image in User Profile Close.....


        binding.root.findViewById<BottomNavigationView>(R.id.bottomNavigationView) .setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.bottom_home->{
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_Profile -> {
                    replaceFragment(ProfileFragment())

                    true
                }
                R.id.bottom_Favourite -> {

                    replaceFragment(FavouriteFragment())

                    true
                }R.id.bottom_cart ->{

                replaceFragment(BottomCartFragment())
                true
                }else->{
                    false
                }

            }

        }
        replaceFragment(HomeFragment())

    }

    // replace fragment start
    fun replaceFragment(fragment: Fragment,tag:String?=null){
        if (fragment is HomeFragment){
            supportFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(tag).commit()
    }



    fun getuserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }




    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else if(supportFragmentManager.backStackEntryCount==1){

            val exitDialog = AlertDialog.Builder(this)

            exitDialog.setCancelable(false)  //not cancel nahi kya ja sakta

            exitDialog.setTitle("Exit")
            exitDialog.setMessage("are you sure Exit ${userName}")
            exitDialog.setIcon(android.R.drawable.gallery_thumb)
            exitDialog.setPositiveButton("Yes"
            ) { p0, p1 -> super.onBackPressedDispatcher.onBackPressed()
                finish()
            }

            exitDialog.setNegativeButton("No"
            ) { p0, p1 -> }

            exitDialog.show()
        }else{
            super.onBackPressed()

        }


    }








}