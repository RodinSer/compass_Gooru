package com.example.gooru.feature.presentation.profile

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.gooru.core.states.LoadState
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.constant.FORM_DATA
import com.example.gooru.core.extensions.*
import com.example.gooru.databinding.FragmentProfileBinding
import com.example.gooru.feature.domain.model.homepage.user.User
import com.example.gooru.feature.presentation.profile.adapter.UserTariffAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)

    private val viewModel by viewModel<ProfileViewModel>()

    private val getItem = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) bindNewAvatarImage(uri)

        saveAvatarButtonClick(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchNightThem()

        observers()

        binding.avatarEdit.setOnClickListener { getItem.launch(FORM_DATA) }

        binding.activateSubscriptionButton.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToTariffFragment())
        }

        personEditButton()

        contentEditButton()

        changePasswordButton()

        logOut()

    }

    private fun bindNewAvatarImage(uri: Uri?) {
        binding.avatarSaveButton.isVisible = true
        binding.avatar.loadImage(uri)
    }

    private fun saveAvatarButtonClick(uri: Uri?) {
        binding.avatarSaveButton.setOnClickListener {
            viewModel.loadImage(uri)
            binding.avatarSaveButton.isVisible = false
        }
    }

    private fun observers() {
        dataObserver(viewModel.user) { user -> userObserver(user) }

        dataObserver(viewModel.avatar) { url -> binding.avatar.loadImage(url) }

        dataObserver(viewModel.isNightMode) { isChecked -> nightModeListener(isChecked) }

        dataObserver(viewModel.tariffVisibility) { tariffActive ->
            binding.userTariff.root.isVisible = tariffActive
            binding.activateSubscriptionButton.isVisible = !tariffActive
        }

        dataObserver(viewModel.loadState) { state -> loadStateListener(state) }


    }

    private fun personEditButton() =
        binding.personEdit.setOnClickListener {
            createPersonEditDialog { newFirstName, newLastName ->
                viewModel.updateUser(newFirstName, newLastName)
            }
        }

    private fun loadStateListener(state: LoadState) {
        if (state == LoadState.ERROR) showError { }
        binding.progressBarr.isVisible = state == LoadState.LOADING
    }

    private fun contentEditButton() = binding.contactEdit.setOnClickListener {
        createContactEditDialog { number -> viewModel.updateUser(number) }
    }

    private fun changePasswordButton() =
        binding.changePasswordButton.setOnClickListener {
            createChangePasswordDialog { newPassword, oldPassword ->
                viewModel.changePassword(newPassword, oldPassword)
            }
        }

    @SuppressLint("SetTextI18n")
    private fun userObserver(user: User) = binding.apply {
        email.text = user.email
        firstName.text = user.firstName
        lastName.text = user.lastName
        phone.text = user.phoneNumber
        userTariff.name.text = user.tariff?.name
        userTariff.startDate.text = "Дата начала : ${user.tariff?.created}"
        userTariff.finishDate.text = "Дата завершения : ${user.tariff?.finishDate}"
        userTariff.recyclerView.adapter = UserTariffAdapter(user.tariff?.description)
    }

    private fun logOut() {
        binding.logUot.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToStartAuthFragment())
        }
    }

    private fun nightModeListener(isChecked: Boolean) {
        binding.switcher.isChecked = isChecked
        if (isChecked) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun switchNightThem() {
        binding.switcher.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNightMode(isChecked)
        }
    }
}