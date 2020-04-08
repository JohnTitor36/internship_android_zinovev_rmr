package com.lockwood.themoviedb.movies.presentation.ui

import com.lockwood.themoviedb.movies.domain.model.Movie

data class MovieViewState(
    val movieId: Int,
    val movie: Movie?,
    val favoriteMovie: Boolean,
    val loading: Boolean
) {

    companion object {

        val initialState
            get() = MovieViewState(
                movieId = 0,
                movie = null,
                favoriteMovie = false,
                loading = true
            )

    }
}