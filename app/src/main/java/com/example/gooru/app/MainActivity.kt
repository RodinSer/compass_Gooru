package com.example.gooru.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.gooru.R
import com.example.gooru.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nightModeListener(viewModel.nightModeProvider())
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding!!.root)
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
                .navController

        navController!!.addOnDestinationChangedListener { _, destination, _ ->

            baseElementsVisibility(destination)
            navigateMessageChat(destination)
        }

        binding!!.bottomNavigate.setupWithNavController(navController!!)
    }

    private fun baseElementsVisibility(destination: NavDestination) {
        val visibility = if (destination.id == R.id.startAuthFragment
            || destination.id == R.id.chatFragment
            || destination.id == R.id.ticketsFragment
        ) View.GONE
        else
            View.VISIBLE

        binding!!.bottomNavigate.visibility = visibility
        binding!!.messageButton.visibility = visibility
    }


    private fun navigateMessageChat(destination: NavDestination) {
        if (destination.id != R.id.chatFragment && destination.id != R.id.ticketsFragment)
            binding!!.messageButton.setOnClickListener {
                navController!!.navigate(R.id.ticketsFragment)
            }
    }

    private fun nightModeListener(isChecked: Boolean) {
        if (isChecked) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}