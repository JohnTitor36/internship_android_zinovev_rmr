package com.lockwood.themoviedb.user.remote.model.body

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteSessionBodyModel(
    @Json(name = "session_id")
    val session_id: String // 1531f1a558c8357ce8990cf887ff196e8f5402ec
)