package com.example.firebaseecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseecommerce.databinding.ActivityForgotPassBinding

class ForgotPassActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgotPassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnpassreset.setOnClickListener {

        }
    }
}