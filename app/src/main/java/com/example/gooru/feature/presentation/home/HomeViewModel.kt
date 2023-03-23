package com.example.gooru.feature.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.provide.AuthTokenProvider
import com.example.gooru.feature.data.home.ListHorizontal
import com.example.gooru.feature.data.home.ListVertical
import com.example.gooru.feature.data.home.creteFAQList
import com.example.gooru.core.provide.UserIdProvider
import com.example.gooru.feature.domain.model.homepage.HomeInfo
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.model.homepage.user.User
import com.example.gooru.feature.domain.useCase.parsource.ParSourceUseCase
import com.example.gooru.feature.domain.useCase.tariff.AllTariffUseCase
import com.example.gooru.feature.domain.useCase.tariff.PayUseCase
import com.example.gooru.feature.domain.useCase.user.UserInfoUseCase
import com.example.gooru.feature.presentation.tabparsource.parser.addparsource.AddParSourceViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dispatchers: DispatchersWrapper,
    private val parSourceUseCase: ParSourceUseCase,
    private val userUseCase: UserInfoUseCase,
    private val userIdProvider: UserIdProvider,
    private val allTariffUseCase: AllTariffUseCase,
    private val payUseCase: PayUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val tokenProvider: AuthTokenProvider
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<HomeInfo>>(emptyList())
    val data = _data.asStateFlow()

    private val _user = MutableSharedFlow<User>(replay = 1)
    val user = _user.asSharedFlow()

    init {
        getHomePage(checkToken())
    }

    fun getHomePage(isAuth: Boolean) = viewModelScope.launch(dispatchers.io + handler) {
        if (isAuth) {
            _loadState.value = LoadState.LOADING
            val myParSource = async { parSourceUseCase.getMyParSource() }.await()
            val userMe = async { userUseCase.getUserInfo() }.await()
            val tariff = async { allTariffUseCase.getAllTariff() }.await()

            _data.value = listOf(
                ListHorizontal("Добро пожаловать", listOf(userMe)),
                ListHorizontal("Мои Парсеры", myParSource),
                ListHorizontal("Мои Тарифы", tariff),
                ListVertical("Как это работает?", creteFAQList())
            )
            _user.emit(userMe)
            _loadState.value = LoadState.SUCCESS
            userIdProvider.putUserID(userMe.id)
        }
    }

    fun getPayUrl(tariffId: Int, redirect: (url: String) -> Unit)=
        viewModelScope.launch(dispatchers.io + handler) {
            _loadState.value = LoadState.LOADING
            val payUrl = payUseCase.getUrl(tariffId)
            _loadState.value = LoadState.SUCCESS
            redirect(payUrl)
        }


    fun checkNewItem() {
        val newParSource: ParSourceHome? = savedStateHandle[AddParSourceViewModel.NEW_PAR_SOURCE]

        if (newParSource != null) {
            val horizontalItems = (_data.value[1] as ListHorizontal)
            val list = horizontalItems.list.toMutableList()

            list.add(newParSource)

            horizontalItems.list = list

            val buildList = _data.value.toMutableList()
            buildList[1] = horizontalItems
            _data.value = buildList
            savedStateHandle[AddParSourceViewModel.NEW_PAR_SOURCE] = null
        }
    }

    private fun checkToken(): Boolean = if (tokenProvider.tokenContain()) true
    else {
        _loadState.value = LoadState.NO_AUTH
        false
    }


}