package com.example.gooru.feature.presentation.parsers.parser.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.R
import com.example.gooru.core.extensions.setItemTouchHelper
import com.example.gooru.databinding.FragmentFavoriteParserBinding
import com.example.gooru.core.base.parser.BaseParserFragment

class FavoriteParserFragment :
    BaseParserFragment<FragmentFavoriteParserBinding, FavoriteParserViewModel>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentFavoriteParserBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset_left_button))

        createViewModel<FavoriteParserViewModel>()

        binding.recyclerView.adapter = adapter

        binding.recyclerView.itemAnimator = null

        dataObserver(viewModel.parsers) { adapter.submitData(it) }
    }
}