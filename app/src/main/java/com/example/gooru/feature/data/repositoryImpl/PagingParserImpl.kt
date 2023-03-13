package com.example.gooru.feature.data.repositoryImpl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gooru.feature.domain.repository.PagingParser
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase
import com.example.gooru.feature.data.PagingSoursParser
import com.example.gooru.feature.domain.model.parser.Parser
import kotlinx.coroutines.flow.Flow

class PagingParserImpl(private val useCase: GetParserUseCase) : PagingParser {

    override fun getParser(parSourceId: Int): Flow<PagingData<Parser>> {
        Log.d("Kart", "Impl")

        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PagingSoursParser(useCase, parSourceId) }
        ).flow
    }
}