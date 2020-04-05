package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.extensions.toDate
import com.lockwood.core.extensions.toFormatString
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class DateAdapter {

    companion object {

        private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
    }

    @ToJson
    fun toJson(date: Date): String {
        return date.toFormatString(DEFAULT_DATE_FORMAT)
    }

    @FromJson
    fun fromJson(date: String): Date {
        return date.toDate(DEFAULT_DATE_FORMAT)
    }

}