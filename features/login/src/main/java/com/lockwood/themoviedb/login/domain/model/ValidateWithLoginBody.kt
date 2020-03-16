package com.lockwood.themoviedb.login.domain.model

data class ValidateWithLoginBody(
    val username: String,
    val password: String,
    val requestToken: String
)