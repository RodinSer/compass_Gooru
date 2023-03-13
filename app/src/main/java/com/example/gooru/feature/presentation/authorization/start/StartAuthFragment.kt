package com.example.gooru.feature.presentation.authorization.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.R
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentStartAuthBinding
import com.google.android.material.tabs.TabLayoutMediator

class StartAuthFragment : BaseFragment<FragmentStartAuthBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentStartAuthBinding.inflate(inflater)

    private val tabs by lazy { resources.getStringArray(R.array.auth_group) }

    private val tabLayoutMediator by lazy {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabs[position]
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.isUserInputEnabled = false

        binding.viewPager.adapter = AuthAdapter(this, tabs.size)
        tabLayoutMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayoutMediator.detach()
    }
}