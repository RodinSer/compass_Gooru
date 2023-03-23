package com.example.gooru.feature.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase
import kotlinx.coroutines.delay

class PagingSoursParser(
    private val parserUseCase: GetParserUseCase,
    private val parSourceId: Int?,
    private val radioButtonId: Int
) : PagingSource<Int, Parser>() {

    override fun getRefreshKey(state: PagingState<Int, Parser>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Parser> {
        val page = params.key ?: FIRST_PAGE
        delay(100) /** фикс лага таб лайаута в таб фрагменте */

        return kotlin.runCatching { parserUseCase.getParser(page, parSourceId, radioButtonId) }
            .fold(
                onSuccess = {
                    LoadResult.Page(
                        prevKey = it.prevPage,
                        data = it.list,
                        nextKey = it.nextPage
                    )
                },
                onFailure = {
                    LoadResult.Error(it)
                }
            )
    }

    private companion object {
        const val FIRST_PAGE = 1
    }
}