package com.example.gooru.feature.presentation.parser.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.example.gooru.R
import com.example.gooru.core.LoadState
import com.example.gooru.core.ParserButton
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showError
import com.example.gooru.core.extensions.showShareDialog
import com.example.gooru.core.extensions.startNewApp
import com.example.gooru.core.tach.setItemTouchHelper
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
        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset240))

        dataObserver(viewModel.list) {
            adapter.setItems(it)
            binding.recyclerView.adapter = adapter
        }

        dataObserver(viewModel.loadState) { state -> loadStateListener(state) }
    }

    private fun loadStateListener(state: LoadState) {
        if (state == LoadState.ERROR) showError { viewModel.getFavoriteList() }
        binding.progressBarr.isVisible = state == LoadState.SUCCESS

    }

    private fun onClickItemButton(button: ParserButton) {
        when (button) {
            ParserButton.SHARE -> button.item?.let { showShareDialog(it.shareUrl) }
            ParserButton.LINK -> button.item?.let { startNewApp(it.url) }
            ParserButton.EDIT -> {}
            ParserButton.DOWNLOAD -> {}//viewModel.downLoad(args.parSourceId, button.item?.id)
            ParserButton.FAVORITE -> {}//viewModel.worKFavorite(button.item?.id)
            ParserButton.MESSAGE -> {}
        }
    }

}