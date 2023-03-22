package com.example.gooru.core.extensions

import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import java.util.regex.Pattern

fun TextView.setText(@StringRes id: Int, text: String) {
    this.setText(id)
    val newText = text + this.text
    this.text = newText
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(uri: Uri?) {
    Glide.with(this)
        .load(uri)
        .into(this)
}

fun ImageView.loadCircleImage(url: String?) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .into(this)
}

fun String.emailValidation() =
    Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.passwordValidation(): Boolean {
    val passwordPattern = "(?=.*[a-z])(?=.*\\d)"
    val reg = Regex(passwordPattern)

    return reg.find(this) != null && this.length > 8

}

fun Fragment.showShareDialog(share: String) {

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, share)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    ContextCompat.startActivity(requireContext(), shareIntent, null)

}