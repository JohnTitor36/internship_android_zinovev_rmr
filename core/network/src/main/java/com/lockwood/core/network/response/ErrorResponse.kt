package com.lockwood.core.network.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "status_message")
    val statusMessage: String, // The resource you requested could not be found.
    @Json(name = "status_code")
    val statusCode: Int // 34
)