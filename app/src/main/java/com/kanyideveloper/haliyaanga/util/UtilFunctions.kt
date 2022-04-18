package com.kanyideveloper.haliyaanga.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Long): String {
    val result = Date(timestamp)
    val startCalendar = Calendar.getInstance()
    startCalendar.time = result
    val format = SimpleDateFormat("EEE, d MMM yy HH:mm")
    return format.format(startCalendar.time)
}