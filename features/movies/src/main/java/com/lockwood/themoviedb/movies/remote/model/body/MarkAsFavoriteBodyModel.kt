package com.lockwood.themoviedb.movies.remote.model.body

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkAsFavoriteBodyModel(
    @Json(name = "media_type")
    val mediaType: String, // movie
    @Json(name = "media_id")
    val mediaId: Int, // 550
    @Json(name = "favorite")
    val favorite: Boolean // true
)