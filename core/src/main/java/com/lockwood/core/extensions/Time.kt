package com.lockwood.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(dateFormat: String): Date {
    val format = SimpleDateFormat(dateFormat, Locale.ENGLISH)
    return format.parse(this) ?: throw ParseException("Failed to parse $this with $dateFormat", 0)
}

fun Date.toFormatString(dateFormat: String): String {
    return dateFormat.format(this)
}