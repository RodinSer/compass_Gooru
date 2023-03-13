package com.example.gooru.feature.data.repositoryImpl

import androidx.paging.*
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.useCase.support.SupportAllUseCase
import kotlinx.coroutines.flow.Flow

class PagingSupportTicket(private val supportAllUseCase: SupportAllUseCase) {

    fun getAllTickets(): Flow<PagingData<SupportTicket>> {

        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PagingSoursTickets(supportAllUseCase) }
        ).flow
    }

}

