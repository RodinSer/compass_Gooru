package com.example.gooru.core.extensions

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.gooru.R
import com.example.gooru.feature.presentation.authorization.auth.AuthFragment
import com.example.gooru.feature.presentation.chat.tikets.TicketsFragment
import com.example.gooru.feature.presentation.profile.ProfileFragment
import com.google.android.material.textfield.TextInputEditText

fun ProfileFragment.createPersonEditDialog(
    action: (newFirstName: String, newLastName: String) -> Unit
) {
    val dialogView = LayoutInflater.from(requireContext())
        .inflate(R.layout.edit_person_data_dialog, null, false)
    val editButton = dialogView.findViewById<Button>(R.id.edit_button)
    val lastName = dialogView.findViewById<TextInputEditText>(R.id.last_name)
    val firstName = dialogView.findViewById<TextInputEditText>(R.id.first_name)
    val closeButton = dialogView.findViewById<ImageView>(R.id.close_image)

    val dialog = AlertDialog.Builder(requireContext())
        .setView(dialogView)
        .create()

    editButton.setOnClickListener {
        action(firstName.text.toString(), lastName.text.toString())
        dialog.dismiss()
    }

    closeButton.setOnClickListener { dialog.dismiss() }

    dialog.show()
}

fun ProfileFragment.createContactEditDialog(
    action: (phoneNumber: String) -> Unit
) {
    val dialogView = LayoutInflater.from(requireContext())
        .inflate(R.layout.edit_contact_data_dialog, null, false)
    val editButton = dialogView.findViewById<Button>(R.id.edit_button)
    val phoneNumber = dialogView.findViewById<TextInputEditText>(R.id.phone_number)
    val closeButton = dialogView.findViewById<ImageView>(R.id.close_image)

    val dialog = AlertDialog.Builder(requireContext())
        .setView(dialogView)
        .create()

    editButton.setOnClickListener {
        action(phoneNumber.text.toString())
        dialog.dismiss()
    }

    closeButton.setOnClickListener { dialog.dismiss() }

    dialog.show()
}

fun ProfileFragment.createChangePasswordDialog(
    action: (newPassword: String, oldPassword: String) -> Unit
) {
    val dialogView = LayoutInflater.from(requireContext())
        .inflate(R.layout.dialog_change_password, null, false)
    val editButton = dialogView.findViewById<Button>(R.id.edit_button)
    val newPassword = dialogView.findViewById<TextInputEditText>(R.id.new_password)
    val oldPassword = dialogView.findViewById<TextInputEditText>(R.id.old_password)
    val closeButton = dialogView.findViewById<ImageView>(R.id.close_image)

    val dialog = AlertDialog.Builder(requireContext())
        .setView(dialogView)
        .create()

    editButton.setOnClickListener {
        action(newPassword.text.toString(), oldPassword.text.toString())
        dialog.dismiss()
    }

    closeButton.setOnClickListener { dialog.dismiss() }

    dialog.show()
}

fun AuthFragment.createResetPasswordDialog(
    email: String,
    action: (email: String) -> Unit
) {
    val dialogView = LayoutInflater.from(requireContext())
        .inflate(R.layout.dialog_reset_password, null, false)
    val resetPassword = dialogView.findViewById<Button>(R.id.next_button)
    val closeButton = dialogView.findViewById<ImageView>(R.id.close_button)
    val sendEmail = dialogView.findViewById<TextInputEditText>(R.id.email)

    sendEmail.setText(email)

    val dialog = AlertDialog.Builder(requireContext())
        .setView(dialogView)
        .create()

    resetPassword.setOnClickListener {
        action(sendEmail.text.toString())
        dialog.dismiss()
    }

    closeButton.setOnClickListener { dialog.dismiss() }

    dialog.show()
}

fun Fragment.showError(
    action: () -> Unit
) {
    val dialogView = LayoutInflater.from(requireContext())
        .inflate(R.layout.dialog_error_internet, null, false)
    val repeatButton:Button = dialogView.findViewById(R.id.repeat_button)

    val dialog = AlertDialog.Builder(requireContext())
        .setView(dialogView)
        .create()

    repeatButton.setOnClickListener {
        action()
        dialog.dismiss()
    }
    dialog.show()
}


fun TicketsFragment.createNewTicketDialog(
    action: (message: String, theme: String) -> Unit
) {
    val dialogView = LayoutInflater.from(requireContext())
        .inflate(R.layout.dialog_create_ticket, null, false)
    val createButton = dialogView.findViewById<Button>(R.id.create_button)
    val closeButton = dialogView.findViewById<ImageView>(R.id.close_image)
    val message = dialogView.findViewById<TextInputEditText>(R.id.message)
    val theme = dialogView.findViewById<TextInputEditText>(R.id.theme)

    val dialog = AlertDialog.Builder(requireContext())
        .setView(dialogView)
        .create()

    createButton.setOnClickListener {
        action(message.text.toString(), theme.text.toString())
        dialog.dismiss()
    }

    closeButton.setOnClickListener { dialog.dismiss() }
    dialog.show()
}