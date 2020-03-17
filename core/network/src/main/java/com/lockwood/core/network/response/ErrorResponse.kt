package com.lockwood.core.network.response

import androidx.annotation.Keep
import com.lockwood.core.network.common.JsonConstants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = JsonConstants.STATUS_MESSAGE)
    val statusMessage: String, // The resource you requested could not be found.
    @Json(name = JsonConstants.STATUS_CODE)
    val statusCode: Int // 34
)