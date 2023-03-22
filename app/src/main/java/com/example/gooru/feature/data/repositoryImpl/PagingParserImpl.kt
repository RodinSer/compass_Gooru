package com.example.gooru.feature.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gooru.feature.domain.repository.PagingParser
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase
import com.example.gooru.feature.data.PagingSoursParser
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.presentation.parser.parser.ParserGrope
import kotlinx.coroutines.flow.Flow

class PagingParserImpl(private val useCase: GetParserUseCase) : PagingParser {

    override fun getParserByParSourceId(
        parSourceId: Int,
        radioButtonId: Int
    ): Flow<PagingData<Parser>> {

        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PagingSoursParser(useCase, parSourceId, radioButtonId) }
        ).flow
    }

    override fun getAllFavoriteParser(): Flow<PagingData<Parser>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PagingSoursParser(useCase, null, ParserGrope.FAVORITE.int) }
        ).flow
    }
}