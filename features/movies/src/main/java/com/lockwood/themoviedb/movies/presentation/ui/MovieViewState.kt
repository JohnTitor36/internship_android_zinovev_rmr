package com.lockwood.themoviedb.movies.presentation.ui

data class MovieViewState(
    val movieId: Int,
    val favoriteMovie: Boolean,
    val loading: Boolean
) {

    companion object {

        val initialState
            get() = MovieViewState(
                movieId = 0,
                favoriteMovie = false,
                loading = true
            )

    }
}