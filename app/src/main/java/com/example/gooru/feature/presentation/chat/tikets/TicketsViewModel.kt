package com.example.gooru.feature.presentation.chat.tikets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.gooru.feature.data.repositoryImpl.PagingSupportTicket

class TicketsViewModel(
    pagingTickets: PagingSupportTicket
) : ViewModel() {

    val tickets = pagingTickets.getAllTickets().cachedIn(viewModelScope)

}