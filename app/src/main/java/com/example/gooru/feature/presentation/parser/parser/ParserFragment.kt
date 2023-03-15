package com.example.gooru.feature.presentation.parser.parser

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

    private val mapRadio by lazy {
        mapOf(
            R.id.radio_all to "ALL",
            R.id.radio_favorite to "favorite",
            R.id.radio_comment to "comment"
        )
    }

    private val args by navArgs<ParserFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsRecyclerView()

        dataObserver(viewModel.parsers(args.parSourceId)) { adapter.submitData(it) }

        binding.radioGroup.check(R.id.radio_all)

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            Log.e("Kart", mapRadio[radioGroup.checkedRadioButtonId].toString())
        }

    }

    private fun settingsRecyclerView(){
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset240))
    }

    private fun onClickItemButton(button: ParserButton) {
        when (button) {
            ParserButton.SHARE -> button.item?.let { showShareDialog(it.shareUrl) }
            ParserButton.LINK -> button.item?.let { startNewApp(it.url) }
            ParserButton.EDIT -> viewModel.editParser(button.item)
            ParserButton.DOWNLOAD -> viewModel.downLoad(args.parSourceId, button.item?.id)
            ParserButton.FAVORITE -> viewModel.worKFavorite(button.item)
            ParserButton.MESSAGE -> {}
        }
    }
}