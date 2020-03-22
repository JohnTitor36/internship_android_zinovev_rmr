package com.lockwood.core.network.extensions

import com.lockwood.core.network.response.ErrorResponse
import com.squareup.moshi.Moshi
import okhttp3.Response

fun Moshi.parseStatusMessage(response: Response): String?{
    response.body?.let {
        val json = it.source()
        val errorResponse = adapter(ErrorResponse::class.java).fromJson(json)
        return errorResponse?.statusMessage
    }
    return null
}