package com.example.gooru.feature.presentation.parser.parser.parserp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.gooru.R
import com.example.gooru.core.extensions.setItemTouchHelper
import com.example.gooru.databinding.FragmentParserBinding
import com.example.gooru.feature.presentation.parser.parser.base.BaseParserFragment

class ParserFragment : BaseParserFragment<FragmentParserBinding, ParserViewModel>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentParserBinding.inflate(inflater)

    private val args by navArgs<ParserFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViewModel<ParserViewModel>()

        settingsRecyclerView()

        binding.radioGroup.check(R.id.radio_favorite)

        viewModel.getParsers(args.parSourceId)

        dataObserver(viewModel.parsers) { adapter.submitData(it) }

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            viewModel.setRadioButtonId(radioGroup.checkedRadioButtonId)
        }
    }

    private fun settingsRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset240))
    }

}

