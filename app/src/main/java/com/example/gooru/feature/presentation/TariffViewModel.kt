package com.example.gooru.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.states.LoadState
import com.example.gooru.feature.data.home.ListHorizontal
import com.example.gooru.feature.data.home.ListVertical
import com.example.gooru.feature.domain.model.homepage.tariff.Tariff
import com.example.gooru.feature.domain.useCase.tariff.AllTariffUseCase
import com.example.gooru.feature.domain.useCase.tariff.PayUseCase
import com.example.gooru.feature.presentation.home.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TariffViewModel(
    private val tariffUseCase: AllTariffUseCase,
    private val payUseCase: PayUseCase,
    private val dispatcher: DispatchersWrapper,
) : BaseViewModel() {

    private val _tariff = MutableStateFlow(emptyList<Tariff>())
    val tariff = _tariff.asStateFlow()

    init {
        getTariff()
    }

    private fun getTariff() = viewModelScope.launch(dispatcher.io + handler) {
        _loadState.value = LoadState.LOADING
        _tariff.value = tariffUseCase.getAllTariff()
        _loadState.value = LoadState.SUCCESS
    }

    fun getPayUrl(tariffId: Int, redirect: (url: String) -> Unit) =
        viewModelScope.launch(dispatcher.io + handler) {
            _loadState.value = LoadState.LOADING
            val payUrl = payUseCase.getUrl(tariffId)
            _loadState.value = LoadState.SUCCESS
            redirect(payUrl)
        }
}