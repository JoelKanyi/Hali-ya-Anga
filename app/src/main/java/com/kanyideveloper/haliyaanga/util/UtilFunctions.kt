package com.kanyideveloper.haliyaanga.util

import android.annotation.SuppressLint
import java.text.DateFormat
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

@SuppressLint("SimpleDateFormat")
fun dayOfTheWeek(mDate: String): String {
    val locale = Locale.getDefault()
    val date = SimpleDateFormat("yyyy-MM-dd").parse(mDate)
    val weekdayNameFormat: DateFormat = SimpleDateFormat("EEE", locale)
    return weekdayNameFormat.format(date as Date)
}