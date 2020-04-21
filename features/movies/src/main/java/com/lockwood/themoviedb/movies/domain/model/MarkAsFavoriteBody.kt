package com.lockwood.themoviedb.movies.domain.model

data class MarkAsFavoriteBody(
    val mediaType: String, // movie
    val mediaId: Int, // 550
    val favorite: Boolean // true
)