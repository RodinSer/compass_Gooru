package com.example.gooru.core.extensions

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.startNewApp(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}