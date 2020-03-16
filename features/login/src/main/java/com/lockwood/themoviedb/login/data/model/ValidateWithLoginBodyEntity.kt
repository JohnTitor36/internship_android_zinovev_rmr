package com.lockwood.themoviedb.login.data.model

data class ValidateWithLoginBodyEntity(
    val username: String,
    val password: String,
    val requestToken: String
)