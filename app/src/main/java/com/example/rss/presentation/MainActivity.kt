package com.example.rss.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rss.R
import com.example.rss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}