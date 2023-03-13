package com.example.gooru.feature.domain.repository

import androidx.paging.PagingData
import com.example.gooru.feature.domain.model.parser.Parser
import kotlinx.coroutines.flow.Flow

interface PagingParser {

    fun getParser(parSourceId:Int): Flow<PagingData<Parser>>
}