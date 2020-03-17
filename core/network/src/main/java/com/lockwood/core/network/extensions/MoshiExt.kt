package com.lockwood.core.network.extensions

import com.lockwood.core.network.response.ErrorResponse
import com.squareup.moshi.Moshi
import okhttp3.Response

fun Moshi.parseStatusMessage(response: Response): String {
    return if (response.body != null) {
        val json = response.body!!.source()
        val errorResponse = adapter(ErrorResponse::class.java).fromJson(json)
        errorResponse?.statusMessage ?: response.message
    } else {
        response.message
    }
}