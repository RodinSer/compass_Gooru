package com.example.gooru.feature.presentation.parser.parsource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showError
import com.example.gooru.databinding.FragmentMyParSourceBinding
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.presentation.parser.adapters.ParSourceAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyParSourceFragment : BaseFragment<FragmentMyParSourceBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentMyParSourceBinding.inflate(inflater)

    private val viewModel by viewModel<MyParSourceViewModel>()

    private val adapter by lazy { ParSourceAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObserver(viewModel.list) { parSources -> setAdapter(parSources) }

        dataObserver(viewModel.loadState) { state -> loadStateListener(state) }
    }

    private fun loadStateListener(state: LoadState) {
        binding.progressBarr.isVisible = state == LoadState.LOADING
        if (state == LoadState.ERROR) showError { viewModel.getMyParSource() }
    }

    private fun setAdapter(parSources: List<ParSourceHome>) {
        adapter.setList(parSources)
        binding.recyclerView.adapter = adapter
    }

}