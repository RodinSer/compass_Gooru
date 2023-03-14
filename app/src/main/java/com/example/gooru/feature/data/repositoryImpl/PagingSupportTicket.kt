package com.example.gooru.feature.data.repositoryImpl

import androidx.paging.*
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.useCase.support.SupportAllTicketsUseCase
import kotlinx.coroutines.flow.Flow

class PagingSupportTicket(private val supportAllUseCase: SupportAllTicketsUseCase) {

   private val pager = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { PagingSoursTickets(supportAllUseCase) }
    )

    fun getAllTickets(): Flow<PagingData<SupportTicket>> {

        return pager.flow
    }

    fun refresh(){
        PagingSoursTickets(supportAllUseCase).invalidate()
    }
}

