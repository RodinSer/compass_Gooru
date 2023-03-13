package com.example.gooru.feature.presentation.authorization.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.gooru.R
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.constant.OFFER
import com.example.gooru.core.extensions.showError
import com.example.gooru.core.extensions.startNewApp
import com.example.gooru.databinding.FragmentRegistrationBinding
import com.example.gooru.feature.presentation.authorization.AuthState
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentRegistrationBinding.inflate(inflater)

    private val viewModel by viewModel<RegistrationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObserver(viewModel.stateRegistration) { state -> loadObserver(state) }

        clickRegistrationButton()

        clickOffer()

    }

    private fun loadObserver(state: AuthState) {
        binding.progressBarr.isVisible = state == AuthState.LOADING
        binding.nextButton.isVisible = state != AuthState.LOADING
        binding.errorMessage.isVisible = state == AuthState.ERROR_400
        if (state == AuthState.SUCCESS_AUTH)
            findNavController().navigate(R.id.action_startAuthFragment_to_emailVerificationFragment)
        if (state == AuthState.ERROR_WI_FI) showError { startRegistration() }
    }

    private fun clickRegistrationButton() =
        binding.nextButton.setOnClickListener {
            startRegistration()
        }

    private fun clickOffer(){
        binding.offer.setOnClickListener { startNewApp(OFFER) }
    }

    private fun startRegistration() {
        viewModel.registration(
            binding.email.text.toString(),
            binding.password.text.toString(),
            binding.repeatPassword.text.toString(),
        )
    }

}