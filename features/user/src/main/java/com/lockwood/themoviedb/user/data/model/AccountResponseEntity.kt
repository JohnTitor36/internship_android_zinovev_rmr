package com.lockwood.themoviedb.user.data.model

data class AccountResponseEntity(
    val gravatarUrl: String,
    val id: Int, // 548
    val iso6391: String, // en
    val iso31661: String, // CA
    val name: String, // Travis Bell
    val includeAdult: Boolean, // true
    val username: String // travisbell
)