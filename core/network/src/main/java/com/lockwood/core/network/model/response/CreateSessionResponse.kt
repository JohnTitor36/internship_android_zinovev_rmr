package com.lockwood.core.network.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
data class CreateSessionResponse(
    @Json(name = "success")
    val success: Boolean, // true
    @Json(name = "session_id")
    val sessionId: String // 1531f1a558c8357ce8990cf887ff196e8f5402ec
)