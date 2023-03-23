package com.example.gooru.core.generation

import com.example.gooru.feature.presentation.tabparsource.parser.addparsource.AddParSourceViewModel

class ExchangeParsing(
    val id: Int,
    val name: String,
    var isSelected:Boolean = false
)

fun AddParSourceViewModel.createExchangeParsing(): List<ExchangeParsing> {
    return listOf(
        ExchangeParsing(1, "Freelance"),
        ExchangeParsing(2, "Kwork"),
        ExchangeParsing(3, "VK"),
        ExchangeParsing(4, "Telegram"),
        ExchangeParsing(5, "HH"),
    )
}