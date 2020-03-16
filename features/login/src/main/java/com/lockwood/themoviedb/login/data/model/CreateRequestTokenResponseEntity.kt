package com.lockwood.themoviedb.login.data.model

data class CreateRequestTokenResponseEntity(
    val success: Boolean,
    val expiresAt: String,
    val requestToken: String
)