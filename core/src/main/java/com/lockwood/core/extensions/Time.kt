package com.lockwood.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"

fun String.toDate(dateFormat: String = DEFAULT_DATE_FORMAT): Date {
    val format = SimpleDateFormat(dateFormat, Locale.ENGLISH)
    return format.parse(this) ?: throw ParseException("Failed to parse $this with $dateFormat", 0)
}

fun Date.toFormatString(dateFormat: String = DEFAULT_DATE_FORMAT): String {
    return dateFormat.format(this)
}