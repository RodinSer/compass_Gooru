package com.example.gooru.feature.presentation.chat.tikets

import androidx.lifecycle.viewModelScope
import com.example.gooru.core.states.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.useCase.support.SupportAllTicketsUseCase
import com.example.gooru.feature.domain.useCase.support.SupportNewTicketUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TicketsViewModel(
    //private val pagingTickets: PagingSupportTicket,/** пагинация*/
    private val ticketUseCase: SupportAllTicketsUseCase,
    private val newTicketUseCase: SupportNewTicketUseCase,
    private val dispatcher: DispatchersWrapper
) : BaseViewModel() {

    private val _tickets = MutableStateFlow(mutableListOf<SupportTicket>())
    val ticket = _tickets.asStateFlow()

    init {
        getTickets()
    }

    fun getTickets() =
        viewModelScope.launch(dispatcher.io + handler) {
            _loadState.value = LoadState.LOADING
            _tickets.value = ticketUseCase.getAllTickets(0).toMutableList()
            _loadState.value = LoadState.SUCCESS
        }

    /** Пагинация*/
    //var tickets = pagingTickets.getAllTickets()

    fun creteNewTicket(name: String, message: String, function: () -> Unit?) =
        viewModelScope.launch(dispatcher.io + handler) {
            _loadState.value = LoadState.LOADING
            _tickets.value.add(0, newTicketUseCase.create(name, message))
            _loadState.value = LoadState.SUCCESS
            function()
        }

}