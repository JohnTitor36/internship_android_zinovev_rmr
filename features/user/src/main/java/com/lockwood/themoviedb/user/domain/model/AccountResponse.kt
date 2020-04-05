package com.lockwood.themoviedb.user.domain.model

import com.lockwood.core.data.GravatarUrl

data class AccountResponse(
    val gravatarUrl: GravatarUrl,
    val id: Int, // 548
    val iso6391: String, // en
    val iso31661: String, // CA
    val name: String, // Travis Bell
    val includeAdult: Boolean, // true
    val username: String // travisbell
)