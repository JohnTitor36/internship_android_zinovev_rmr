package com.lockwood.themoviedb.user.data.model

import com.lockwood.core.data.GravatarUrl

data class AccountResponseEntity(
    val gravatarUrl: GravatarUrl,
    val id: Int, // 548
    val iso6391: String, // en
    val iso31661: String, // CA
    val name: String, // Travis Bell
    val includeAdult: Boolean, // true
    val username: String // travisbell
)