package com.lockwood.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(dateFormat: String): Date {
    val format = SimpleDateFormat(dateFormat, Locale.ENGLISH)
    return format.parse(this) ?: Date()
}

fun Date.toFormatString(dateFormat: String): String {
    return dateFormat.format(this)
}