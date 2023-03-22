package com.example.gooru.feature.presentation.authorization.start

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gooru.feature.presentation.authorization.auth.AuthFragment
import com.example.gooru.feature.presentation.authorization.registration.RegistrationFragment

class AuthAdapter(
    fragment: StartAuthFragment,
    private val size: Int,
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AuthFragment()
            1 -> RegistrationFragment()
            else -> Fragment()
        }
    }
}