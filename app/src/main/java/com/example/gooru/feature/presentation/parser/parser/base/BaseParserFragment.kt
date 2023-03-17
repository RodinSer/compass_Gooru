package com.example.gooru.feature.presentation.parser.parser.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.gooru.core.ParserButton
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showShareDialog
import com.example.gooru.core.extensions.startNewApp
import com.example.gooru.feature.presentation.parser.adapters.ParserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseParserFragment<V : ViewBinding,VM:BaseParserViewModel> : BaseFragment<V>() {

    protected inline fun <reified V : BaseParserViewModel> createViewModel() {
        val newViewModel by viewModel<V>()
        viewModel = newViewModel as VM
    }

    protected lateinit var viewModel: VM

    protected val adapter = ParserAdapter(::onClickItemButton)

    protected var parSourceId = -1

    private fun onClickItemButton(button: ParserButton) {
        Log.e("Kart","$button")
        when (button) {
            ParserButton.SHARE -> button.item?.let { showShareDialog(it.shareUrl) }
            ParserButton.LINK -> button.item?.let { startNewApp(it.url) }
            ParserButton.EDIT -> viewModel.editParser(button.item)
            ParserButton.DOWNLOAD -> viewModel.downLoad(parSourceId, button.item?.id)
            ParserButton.FAVORITE -> {
                viewModel.worKFavorite(button.item)
                adapter.refresh()
            }
            ParserButton.COMMENT -> viewModel.commentWork(button.item)
        }
    }
}