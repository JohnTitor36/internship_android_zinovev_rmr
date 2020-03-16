package com.lockwood.themoviedb.login.domain.model

data class CreateRequestTokenResponse(
    val success: Boolean,
    val expiresAt: String,
    val requestToken: String
)