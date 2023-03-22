package com.example.gooru.feature.presentation.parser.parsource

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar.Tab
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.gooru.R
import com.example.gooru.TestFragment
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showError
import com.example.gooru.databinding.FragmentMyParSourceBinding
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.presentation.parser.adapters.ParSourceAdapter
import com.example.gooru.feature.presentation.parser.tab.TabParSourceFragment
import com.example.gooru.feature.presentation.parser.tab.TabParSourceFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyParSourceFragment : BaseFragment<FragmentMyParSourceBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentMyParSourceBinding.inflate(inflater)

    private val viewModel by viewModel<MyParSourceViewModel>()

    private val adapter by lazy { ParSourceAdapter(::onClickListener) }

    override fun onStart() {
        super.onStart()
        viewModel.checkNewItem()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        dataObserver(viewModel.list) { parSources -> setAdapter(parSources) }

        dataObserver(viewModel.loadState) { state -> loadStateListener(state) }

        binding.addParSource.setOnClickListener {
        findNavController().navigate(TabParSourceFragmentDirections.actionTabParSourceFragmentToAddParSourceFragment())
        }
    }

    private fun loadStateListener(state: LoadState) {
        binding.progressBarr.isVisible = state == LoadState.LOADING
        if (state == LoadState.ERROR) showError { viewModel.getMyParSource() }
    }

    private fun setAdapter(parSources: List<ParSourceHome>) {
        adapter.setList(parSources)
        binding.recyclerView.adapter = adapter
    }

    private fun onClickListener(parSourceId: Int) {
        findNavController().navigate(
            TabParSourceFragmentDirections.actionTabParSourceFragmentToParserFragment(
                parSourceId
            )
        )


    }

}