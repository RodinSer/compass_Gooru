package com.example.gooru.feature.data.dto.parsource

import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome

class NewParSourceDto(
   private val data_source: String,
   private val data_type: Int,
   private val description: String,
   private val freelance_source: List<Int>,
   private val id: Int,
   private val keywords: String,
   private val name: String,
   private val parse_fields: Any,
   private val url_detail: Any,
   private val user: Int
) {
    fun toParSource() = ParSourceHome(id, "Now", name, "Progress", keywords)
}