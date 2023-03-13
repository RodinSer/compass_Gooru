package com.example.gooru.feature.presentation.parser.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentFavoriteParserBinding
import com.example.gooru.feature.presentation.parser.adapters.FavoriteListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteParserFragment : BaseFragment<FragmentFavoriteParserBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentFavoriteParserBinding.inflate(inflater)

    private val viewModel by viewModel<FavoriteParserViewModel>()

    private val adapter by lazy { FavoriteListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavoriteList()

        dataObserver(viewModel.list) {
            adapter.setItems(it)
            binding.recyclerView.adapter = adapter
        }

    }

}