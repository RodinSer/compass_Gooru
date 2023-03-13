package com.example.gooru.feature.data.repositoryImpl

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.useCase.support.SupportAllUseCase

class PagingSoursTickets(
    private val supportAllUseCase: SupportAllUseCase
) : PagingSource<Int, SupportTicket>() {
    override fun getRefreshKey(state: PagingState<Int, SupportTicket>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SupportTicket> {
        val page = params.key ?: FIRST_PAGE

        val item =
            supportAllUseCase.getAllTickets(page)

        Log.e("Kart", item.first().id.toString())

        return LoadResult.Page(
            prevKey = null,
            data = item,
            nextKey = null
        )
    }

    private companion object {
        const val FIRST_PAGE = 1
    }
}