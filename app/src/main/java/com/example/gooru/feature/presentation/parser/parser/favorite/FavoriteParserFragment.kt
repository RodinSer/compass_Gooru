package com.example.gooru.feature.presentation.parser.parser.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.R
import com.example.gooru.core.extensions.setItemTouchHelper
import com.example.gooru.databinding.FragmentFavoriteParserBinding
import com.example.gooru.feature.presentation.parser.parser.base.BaseParserFragment

class FavoriteParserFragment :
    BaseParserFragment<FragmentFavoriteParserBinding, FavoriteParserViewModel>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentFavoriteParserBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset240))

        createViewModel<FavoriteParserViewModel>()

        binding.recyclerView.adapter = adapter

        dataObserver(viewModel.parsers) { adapter.submitData(it) }
    }
}