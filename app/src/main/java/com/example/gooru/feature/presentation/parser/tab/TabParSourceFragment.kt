package com.example.gooru.feature.presentation.parser.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.R
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentStartAuthBinding
import com.example.gooru.feature.presentation.parser.adapters.ParSourceTabAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TabParSourceFragment : BaseFragment<FragmentStartAuthBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentStartAuthBinding.inflate(inflater)

    private val tabs by lazy { resources.getStringArray((R.array.par_source)) }

    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsTabMediator()

    }

    private fun settingsTabMediator() {
        binding.viewPager.isUserInputEnabled = false

        binding.viewPager.adapter = ParSourceTabAdapter(this, tabs.size)

        tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = tabs[position]
            }.apply { attach() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayoutMediator?.detach()
    }

}