package com.lockwood.themoviedb.movies.presentation.ui.search

import com.lockwood.themoviedb.movies.domain.model.Movie

data class SearchViewState(
    val movieName: String,
    val movieItemType: Int,
    val inputClicked: Boolean,
    val inputStarted: Boolean,
    val movies: List<Movie>
) {

    companion object {

        fun initialState(): SearchViewState {
            return SearchViewState(
                movieName = "",
                movieItemType = 0,
                inputClicked = false,
                inputStarted = false,
                movies = emptyList()
            )
        }

    }
}