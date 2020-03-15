package com.lockwood.core.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
data class NotFoundResponse(
    @Json(name = "status_message")
    val statusMessage: String, // The resource you requested could not be found.
    @Json(name = "status_code")
    val statusCode: Int // 34
)