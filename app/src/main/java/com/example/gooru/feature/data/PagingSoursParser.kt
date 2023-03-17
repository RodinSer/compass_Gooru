package com.example.gooru.feature.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase
import kotlinx.coroutines.delay

class PagingSoursParser(
    private val parserUseCase: GetParserUseCase,
    private val parSourceId: Int?,
    private val radioButtonId:Int
) : PagingSource<Int, Parser>() {

    override fun getRefreshKey(state: PagingState<Int, Parser>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Parser> {
        val page = params.key ?: FIRST_PAGE
        delay(100)
        val item =
            parserUseCase.getParser(page, parSourceId,radioButtonId)

        Log.e("Kart",item.list.size.toString())

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