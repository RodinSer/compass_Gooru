package com.example.gooru.feature.presentation.parser.parser

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.gooru.R
import com.example.gooru.core.ParserButton
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showShareDialog
import com.example.gooru.core.extensions.startNewApp
import com.example.gooru.core.tach.setItemTouchHelper
import com.example.gooru.databinding.FragmentParserBinding
import com.example.gooru.feature.presentation.parser.adapters.ParserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ParserFragment : BaseFragment<FragmentParserBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentParserBinding.inflate(inflater)

    private val viewModel by viewModel<ParserViewModel>()

    private val adapter = ParserAdapter(::onClickItemButton)

    private val mapRadio by lazy { mapOf(R.id.radio_all to "ALL",R.id.radio_favorite to "favorite",R.id.radio_comment to "comment") }

   private val args by navArgs<ParserFragmentArgs>()


    private val downloadManager by lazy {
        requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.infoGroup.visibility = View.GONE

        binding.recyclerView.adapter = adapter

        dataObserver(viewModel.parsers(args.parSourceId)) { adapter.submitData(it) }

        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset240))

        binding.radioGroup.check(R.id.radio_all)

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
           Log.e( "Kart",mapRadio[radioGroup.checkedRadioButtonId].toString())
        }

    }

    private fun onClickItemButton(button: ParserButton) {
        when (button) {
            ParserButton.SHARE -> button.item?.let { showShareDialog(it.shareUrl) }
            ParserButton.LINK -> button.item?.let { startNewApp(it.url) }
            ParserButton.EDIT -> {}
            ParserButton.DOWNLOAD -> {}
            ParserButton.FAVORITE -> viewModel.worKFavorite(button.item?.id)
            ParserButton.MESSAGE -> {}
        }
    }


}