package com.example.gooru.feature.presentation.parser.adapters.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.gooru.feature.domain.model.parser.Parser

class ParserDiff : DiffUtil.ItemCallback<Parser>() {

    override fun areItemsTheSame(oldItem: Parser, newItem: Parser) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Parser, newItem: Parser) = oldItem == newItem

    override fun getChangePayload(oldItem: Parser, newItem: Parser): Parser {

        if (oldItem.id == newItem.id) oldItem.favoriteId = newItem.favoriteId

        return newItem

    }
}
