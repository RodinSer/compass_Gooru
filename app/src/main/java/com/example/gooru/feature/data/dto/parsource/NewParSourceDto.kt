package com.example.gooru.feature.data.dto.parsource

import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome

class NewParSourceDto(
    val data_source: String,
    val data_type: Int,
    val description: String,
    val freelance_source: List<Int>,
    val id: Int,
    val keywords: String,
    val name: String,
    val parse_fields: Any,
    val url_detail: Any,
    val user: Int
) {
    fun toParSource() = ParSourceHome(id, "Now", name)
}