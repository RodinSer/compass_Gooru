package com.example.gooru.feature.presentation.parsers.parser.parserp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.gooru.R
import com.example.gooru.core.extensions.setItemTouchHelper
import com.example.gooru.databinding.FragmentParserBinding
import com.example.gooru.core.base.parser.BaseParserFragment

class ParserFragment : BaseParserFragment<FragmentParserBinding, ParserViewModel>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentParserBinding.inflate(inflater)

    private val args by navArgs<ParserFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createViewModel<ParserViewModel>()

        settingsRecyclerView()

        binding.radioGroup.check(R.id.radio_all)

        viewModel.getParsers(args.parSourceId)

        viewModel.getParSourceInfo(args.parSourceId)

        dataObserver(viewModel.parsers) { parser -> adapter.submitData(parser) }

        dataObserver(viewModel.parSource) { parSource ->
            binding.keyWord.text = parSource?.keywords
            binding.name.text = parSource?.name
            binding.status.text = parSource?.condition
        }

        binding.downloadButton.setOnClickListener {
            viewModel.downloadUrlAllParsersByIdParSource(args.parSourceId)
        }

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            viewModel.setRadioButtonId(radioGroup.checkedRadioButtonId)
        }
    }


    private fun settingsRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset_left_button))
    }

}

