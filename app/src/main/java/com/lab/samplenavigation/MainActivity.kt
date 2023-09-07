package com.lab.samplenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.lab.samplenavigation.databinding.ActivityMainBinding
import com.lab.samplenavigation.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBotNav()

    }

    private fun setupBotNav() {
//        navController = findNavController(binding.fragmentContainer.id)
//        binding.bottomNavigation.setupWithNavController(navController)
    }
}