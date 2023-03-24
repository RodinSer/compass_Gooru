package com.example.gooru.feature.presentation.parsers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.gooru.core.states.ParserButton
import com.example.gooru.databinding.ItemParserBinding
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.presentation.parsers.adapters.diff.ParserDiff
import com.example.gooru.feature.presentation.parsers.adapters.holders.ParserViewHolder

class ParserAdapter(
    private val onClickListener: (button: ParserButton) -> Unit
) : PagingDataAdapter<Parser, ParserViewHolder>(ParserDiff()) {
    override fun onBindViewHolder(holder: ParserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ParserViewHolder(
        ItemParserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onClickListener
    )
}

