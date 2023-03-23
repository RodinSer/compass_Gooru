package com.example.gooru.core.base.parser

import androidx.viewbinding.ViewBinding
import com.example.gooru.core.states.ParserButton
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showShareDialog
import com.example.gooru.core.extensions.startNewApp
import com.example.gooru.feature.presentation.parsers.adapters.ParserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNCHECKED_CAST")
abstract class BaseParserFragment<V : ViewBinding, VM : BaseParserViewModel> : BaseFragment<V>() {

    protected inline fun <reified V : BaseParserViewModel> createViewModel() {
        val newViewModel by viewModel<V>()
        viewModel = newViewModel as VM
    }

    protected lateinit var viewModel: VM

    protected val adapter = ParserAdapter(::onClickItemButton)

    private fun onClickItemButton(button: ParserButton) =
        when (button) {
            ParserButton.SHARE -> button.item?.let { showShareDialog(it.shareUrl) }
            ParserButton.LINK -> button.item?.let { startNewApp(it.url) }
            else -> viewModel.addListenerPersonButton(button)
        }

}