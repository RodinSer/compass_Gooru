package com.example.gooru.feature.presentation.parser.parsource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentMyParSourceBinding
import com.example.gooru.feature.presentation.parser.adapters.ParSourceAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyParSourceFragment : BaseFragment<FragmentMyParSourceBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentMyParSourceBinding.inflate(inflater)

    private val viewModel by viewModel<MyParSourceViewModel>()

    private val adapter by lazy{ ParSourceAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMyParSource()

        dataObserver(viewModel.list){
            adapter.setList(it)
            binding.recyclerView.adapter = adapter
        }
    }

}