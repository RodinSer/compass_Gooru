package com.example.gooru.feature.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase

class PagingSoursParser(
    private val parserRepository: GetParserUseCase,
    private val parSourceId: Int
) : PagingSource<Int, Parser>() {

    override fun getRefreshKey(state: PagingState<Int, Parser>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Parser> {
        val page = params.key ?: FIRST_PAGE

        val item =
            parserRepository.getParser(page, parSourceId)

        return LoadResult.Page(
            prevKey = item.prevPage,
            data = item.list,
            nextKey = item.nextPage
        )

    }

    private companion object {
        const val FIRST_PAGE = 1
    }
}