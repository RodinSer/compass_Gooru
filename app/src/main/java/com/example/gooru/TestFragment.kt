package com.example.gooru

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.showError
import com.example.gooru.databinding.FragmentAddParSourceBinding
import com.example.gooru.feature.presentation.parser.addparsource.AddParSourceViewModel
import com.example.gooru.feature.presentation.parser.addparsource.adapter.ExchangeAdapter
import com.example.gooru.feature.presentation.parser.addparsource.adapter.KeyWordAdapter
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestFragment : DialogFragment() {

    private var _binding: FragmentAddParSourceBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<AddParSourceViewModel>()

    private val adapterExchangeParsing by lazy { ExchangeAdapter() }

    private val adapterKeuWord by lazy { KeyWordAdapter() }

    private val job = Job()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        _binding = FragmentAddParSourceBinding.inflate(layoutInflater)

        // dataObserver(viewModel.loadState) { state -> loadStateObserver(state) }


        setAdapter()

        binding.buttonKeyWord.setEndIconOnClickListener { addKeyWord() }


//        dataObserver(viewModel.isBackStack) { isBackStack -> popToBAckStack(isBackStack) }

        onChangeEditText(binding.name)

        sendButton()


        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()

    }



    private fun loadStateObserver(state: LoadState) {
        binding.progressBarr.isVisible = state == LoadState.LOADING
        when (state) {
            LoadState.ERROR -> showError { createParSource() }
            LoadState.SUCCESS ->
                Toast.makeText(requireContext(), "parSource создан успешно", Toast.LENGTH_SHORT)
                    .show()
            else -> {}
        }
    }

    private fun setAdapter() {
        adapterExchangeParsing.setItems(viewModel.getExchangeParsing())
        binding.recyclerViewExchange.adapter = adapterExchangeParsing
        binding.recyclerViewKeyWord.adapter = adapterKeuWord
    }

    private fun popToBAckStack(isBackStack: Boolean) {
        if (isBackStack) findNavController().popBackStack()
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
        binding.sendButton.setOnClickListener {
            createParSource()
        }

    private fun createParSource() {

        CoroutineScope(job).launch {
            Log.e("Kart", "Start")

          val test =  viewModel.creteParSource(
                1,
                binding.description.text.toString(),
                adapterExchangeParsing.getItems(),
                adapterKeuWord.getItems(),
                binding.name.text.toString()
            )
            parentFragmentManager.setFragmentResult("TEST", bundleOf("Name" to test))
            dismiss()
        }

    }
}