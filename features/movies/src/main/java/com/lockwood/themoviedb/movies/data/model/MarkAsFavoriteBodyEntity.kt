package com.lockwood.themoviedb.movies.data.model

data class MarkAsFavoriteBodyEntity(
    val mediaType: String, // movie
    val mediaId: Int, // 550
    val favorite: Boolean // true
)