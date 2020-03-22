package com.lockwood.themoviedb.login.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionResponseModel(
    @Json(name = "success")
    val success: Boolean, // true
    @Json(name = "session_id")
    val sessionId: String // 1531f1a558c8357ce8990cf887ff196e8f5402ec
)