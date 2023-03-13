package com.example.gooru.core.extensions

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.simpleDateFormat(): String {

    val formatter = SimpleDateFormat("yyyy-MM-dd'T' HH:mm")
    val mDate = formatter.parse(this) as Date// this never ends while debugging
    Log.e("mDate", mDate.toString())
    val newFormat = SimpleDateFormat("dd.MM HH:mm")
    return newFormat.format(mDate)
}