package com.example.gooru.feature.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showError
import com.example.gooru.core.extensions.showShareDialog
import com.example.gooru.core.extensions.startNewApp
import com.example.gooru.databinding.FragmentHomeBinding
import com.example.gooru.feature.presentation.home.adapters.adapter.HomeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModel<HomeViewModel>()

    private val adapter by lazy { HomeAdapter { onClick(it) } }

    override fun onStart() {
        super.onStart()
        viewModel.checkNewItem()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObserver(viewModel.data) { list ->
            adapter.items = list
            binding.recyclerView.adapter = adapter
        }

        dataObserver(viewModel.loadState) { state ->
            binding.progressBarr.isVisible = state == LoadState.LOADING
            if (state == LoadState.ERROR) showError { viewModel.getHomePage(true) }
            if (state == LoadState.NO_AUTH) findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStartAuthFragment())
        }
    }

    private fun onClick(homeButton: HomeButton) = when (homeButton) {
        HomeButton.NEW_SOURCE -> findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAddParSourceFragment()
        )
        HomeButton.PARSER -> findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToParserFragment(homeButton.id)
        )
        HomeButton.TARIFF -> redirect(homeButton.id)
    }

    private fun redirect(id: Int) {
        viewModel.getPayUrl(id) { url ->
            startNewApp(url)
        }
    }

}