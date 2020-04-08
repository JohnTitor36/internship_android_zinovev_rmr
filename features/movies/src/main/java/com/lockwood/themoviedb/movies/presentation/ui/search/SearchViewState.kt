package com.lockwood.themoviedb.movies.presentation.ui.search

import com.lockwood.core.pagination.Pagination
import com.lockwood.themoviedb.movies.domain.model.MovieItem
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType

data class SearchViewState(
    val movieName: String,
    val inputClicked: Boolean,
    val inputStarted: Boolean,
    val inputEmpty: Boolean,
    val movies: List<MovieItem>,
    val viewItemType: Int,
    override var pageCount: Int,
    override var currentPage: Int,
    override var perPage: Int
) : Pagination {

    companion object {

        val initialState
            get() = SearchViewState(
                movieName = "",
                viewItemType = MoviesItemViewType.ITEM_VIEW_TYPE_LIST,
                inputClicked = false,
                inputStarted = false,
                inputEmpty = true,
                movies = emptyList(),
                pageCount = 0,
                currentPage = 1,
                perPage = 0
            )

    }
}