package com.example.gooru.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.gooru.R
import com.example.gooru.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val navController = (supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment).navController

        binding.bottomNavigate.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            binding.bottomNavigate.isVisible =
                destination.id != R.id.startAuthFragment &&
                        destination.id != R.id.ticketsFragment &&
                        destination.id != R.id.chatFragment
            binding.messageButton.isVisible = destination.id != R.id.startAuthFragment

            binding.messageButton.setOnClickListener {
                if (destination.id != R.id.ticketsFragment && destination.id != R.id.chatFragment) {
                    navController.navigate(R.id.ticketsFragment)
                }

            }
        }
    }
}