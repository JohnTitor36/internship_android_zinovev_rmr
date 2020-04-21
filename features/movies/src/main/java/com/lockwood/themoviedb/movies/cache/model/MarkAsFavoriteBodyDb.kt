package com.lockwood.themoviedb.movies.cache.model

data class MarkAsFavoriteBodyDb(
    val mediaType: String, // movie
    val mediaId: Int, // 550
    val favorite: Boolean // true
)