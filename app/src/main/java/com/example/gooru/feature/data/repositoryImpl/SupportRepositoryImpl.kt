package com.example.gooru.feature.data.repositoryImpl

import com.example.gooru.feature.data.api.SupportApi
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.repository.SupportRepository

class SupportRepositoryImpl(
    private val supportApi: SupportApi
) : SupportRepository {

    override suspend fun getAllTickets(page: Int) =
        supportApi.getUserSupports(page).toListSupportTicket()
}