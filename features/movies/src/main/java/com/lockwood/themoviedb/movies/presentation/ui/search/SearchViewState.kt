package com.lockwood.themoviedb.movies.presentation.ui.search

import com.lockwood.core.pagination.Pagination
import com.lockwood.themoviedb.movies.domain.model.Movie

data class SearchViewState(
    val movieName: String,
    val movieItemType: Int,
    val inputClicked: Boolean,
    val inputStarted: Boolean,
    val movies: List<Movie>,
    override var pageCount: Int,
    override var currentPage: Int,
    override var perPage: Int
) : Pagination {

    companion object {

        val initialState
            get() = SearchViewState(
                movieName = "",
                movieItemType = 0,
                inputClicked = false,
                inputStarted = false,
                movies = emptyList(),
                pageCount = 0,
                currentPage = 1,
                perPage = 0
            )

    }
}