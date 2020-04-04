package com.lockwood.themoviedb.user.data.model

data class AccountResponseEntity(
    val avatar: Avatar,
    val id: Int, // 548
    val iso6391: String, // en
    val iso31661: String, // CA
    val name: String, // Travis Bell
    val includeAdult: Boolean, // true
    val username: String // travisbell
) {
    data class Avatar(
        val gravatar: Gravatar
    ) {
        data class Gravatar(
            val hash: String // c9e9fc152ee756a900db85757c29815d
        )
    }
}