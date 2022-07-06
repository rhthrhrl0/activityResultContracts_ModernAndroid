package com.example.activity_transfer_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activity_transfer_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}