package com.lockwood.core.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
data class UnauthorizedResponse(
    @Json(name = "status_message")
    val statusMessage: String, // Invalid API key: You must be granted a valid key.
    @Json(name = "success")
    val success: Boolean, // false
    @Json(name = "status_code")
    val statusCode: Int // 7
)