package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.extensions.toDate
import com.lockwood.core.extensions.toFormatString
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.Date

class DateAdapter {

    @ToJson
    fun toJson(date: Date): String {
        return date.toFormatString()
    }

    @FromJson
    fun fromJson(date: String): Date {
        return date.toDate()
    }

}