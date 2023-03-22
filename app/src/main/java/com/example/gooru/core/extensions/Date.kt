package com.example.gooru.core.extensions

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.simpleDateFormat(format: String = "dd.MM HH:mm"): String {

    val formatter = SimpleDateFormat("yyyy-MM-dd'T' HH:mm")
    val mDate = formatter.parse(this) as Date// this never ends while debugging
    val newFormat = SimpleDateFormat(format)
    return newFormat.format(mDate)
}