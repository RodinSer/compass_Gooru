package com.example.gooru.feature.data.repositoryImpl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.useCase.support.SupportAllTicketsUseCase

class PagingSoursTickets(
    private val supportAllUseCase: SupportAllTicketsUseCase
) : PagingSource<Int, SupportTicket>() {
    override fun getRefreshKey(state: PagingState<Int, SupportTicket>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SupportTicket> {
        val page = params.key ?: FIRST_PAGE

        return kotlin.runCatching { supportAllUseCase.getAllTickets(page) }.fold(
            onSuccess = {
                LoadResult.Page(
                    prevKey = null,
                    data = it,
                    nextKey = null
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        const val FIRST_PAGE = 1
    }
}