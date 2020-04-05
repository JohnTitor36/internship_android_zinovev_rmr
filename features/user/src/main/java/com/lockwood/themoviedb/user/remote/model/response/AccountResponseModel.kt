package com.lockwood.themoviedb.user.remote.model.response

import com.lockwood.core.common.GravatarUrl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountResponseModel(
    @Json(name = "avatar")
    val avatar: Avatar,
    @Json(name = "id")
    val id: Int, // 548
    @Json(name = "iso_639_1")
    val iso6391: String, // en
    @Json(name = "iso_3166_1")
    val iso31661: String, // CA
    @Json(name = "name")
    val name: String, // Travis Bell
    @Json(name = "include_adult")
    val includeAdult: Boolean, // true
    @Json(name = "username")
    val username: String // travisbell
) {
    @JsonClass(generateAdapter = true)
    data class Avatar(
        @Json(name = "gravatar")
        val gravatar: Gravatar
    ) {
        @JsonClass(generateAdapter = true)
        data class Gravatar(
            @Json(name = "hash")
            val url: GravatarUrl // c9e9fc152ee756a900db85757c29815d
        )
    }
}