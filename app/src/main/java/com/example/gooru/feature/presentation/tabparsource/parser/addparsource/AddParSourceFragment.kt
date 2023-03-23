package com.example.gooru.feature.presentation.tabparsource.parser.addparsource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.gooru.core.states.LoadState
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showError
import com.example.gooru.databinding.FragmentAddParSourceBinding
import com.example.gooru.feature.presentation.tabparsource.parser.adapters.ExchangeAdapter
import com.example.gooru.feature.presentation.tabparsource.parser.adapters.KeyWordAdapter
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddParSourceFragment : BaseFragment<FragmentAddParSourceBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentAddParSourceBinding.inflate(inflater)

    private val viewModel by viewModel<AddParSourceViewModel>()

    private val adapterExchangeParsing by lazy { ExchangeAdapter() }

    private val adapterKeuWord by lazy { KeyWordAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObserver(viewModel.loadState) { state -> loadStateObserver(state) }

        setAdapter()

        binding.buttonKeyWord.setEndIconOnClickListener { addKeyWord() }

        onChangeEditText(binding.name)

        sendButton()
    }

    private fun loadStateObserver(state: LoadState) {
        binding.progressBarr.isVisible = state == LoadState.LOADING
        when (state) {
            LoadState.ERROR -> showError { createParSource() }
            LoadState.SUCCESS -> {
                Toast.makeText(requireContext(), "parSource создан успешно", Toast.LENGTH_SHORT)
                    .show()
                findNavController().popBackStack()
            }
            else -> {}
        }
    }

    private fun setAdapter() {
        adapterExchangeParsing.setItems(viewModel.getExchangeParsing())
        binding.recyclerViewExchange.adapter = adapterExchangeParsing
        binding.recyclerViewKeyWord.adapter = adapterKeuWord
    }

    private fun onChangeEditText(editText: TextInputEditText) =
        editText.doOnTextChanged { text, _, _, _ ->
            binding.sendButton.isEnabled = text.toString() != ""
        }

    private fun addKeyWord() {
        val keyWord = binding.keyWord.text.toString()
        if (keyWord != "")
            adapterKeuWord.addWord(keyWord)
        binding.keyWord.setText("")
    }

    private fun sendButton() =
        binding.sendButton.setOnClickListener { createParSource() }

    private fun createParSource() = viewModel.creteParSource(
        1,
        binding.description.text.toString(),
        adapterExchangeParsing.getItems(),
        adapterKeuWord.getItems(),
        binding.name.text.toString()
    )

}