package com.example.gooru.feature.presentation.profile

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.feature.domain.model.homepage.user.User
import com.example.gooru.feature.domain.useCase.tariff.UserTariffUseCase
import com.example.gooru.feature.domain.useCase.user.AvatarUploadUseCase
import com.example.gooru.feature.domain.useCase.user.UserChangePasswordUseCase
import com.example.gooru.feature.domain.useCase.user.UserInfoUseCase
import com.example.gooru.feature.domain.useCase.user.UserUpdateUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val dispatchers: DispatchersWrapper,
    private val userInfoUseCase: UserInfoUseCase,
    private val userUpdateUseCase: UserUpdateUseCase,
    private val changePasswordUseCase: UserChangePasswordUseCase,
    private val uploadUseCase: AvatarUploadUseCase,
    private val tariffUseCase: UserTariffUseCase,
) : BaseViewModel() {

    private var changeUser: User? = null

    private val _user = MutableSharedFlow<User>(replay = 1)
    val user = _user.asSharedFlow()

    private val _avatar = MutableStateFlow<String>("")
    val avatar = _avatar.asSharedFlow()

    private val _tariffVisibility = MutableStateFlow(false)
    val tariffVisibility = _tariffVisibility.asSharedFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() =
        viewModelScope.launch(dispatchers.io + handler) {
            _loadState.value = LoadState.LOADING
            val currentUser = userInfoUseCase.getUserInfo()
            changeUser = currentUser
            changeUser?.tariff = tariffUseCase.getUserTariff()
            _tariffVisibility.value = changeUser?.tariff != null
            _user.emit(currentUser)
            _loadState.value = LoadState.SUCCESS
        }

    fun updateUser(first: String, last: String? = null) =
        viewModelScope.launch(dispatchers.io + handler) {

            if (changeUser != null) {
                _loadState.value = LoadState.LOADING
                _user.emit(userUpdateUseCase.userUpData(changeUser!!.toBody(first, last)))
                _loadState.value = LoadState.SUCCESS
            }

        }

    fun changePassword(newPassword: String, oldPassword: String) =
        viewModelScope.launch(dispatchers.io + handler) {
            _loadState.value = LoadState.LOADING
            changePasswordUseCase.changePassword(newPassword, oldPassword)
            _loadState.value = LoadState.SUCCESS
        }

    fun loadImage(uri: Uri?) =
        viewModelScope.launch(dispatchers.io + handler) {
            _loadState.value = LoadState.LOADING
            _avatar.emit(uploadUseCase.imageUpload(uri, changeUser?.id).avatar)
            _loadState.value = LoadState.SUCCESS
        }

}