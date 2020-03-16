package com.lockwood.themoviedb.login.domain.model

data class CreateSessionResponse(
    val success: Boolean,
    val sessionId: String
)