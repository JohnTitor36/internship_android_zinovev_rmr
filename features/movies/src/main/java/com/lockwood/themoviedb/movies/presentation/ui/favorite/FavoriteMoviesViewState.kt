package com.lockwood.themoviedb.movies.presentation.ui.favorite

import com.lockwood.core.extensions.EMPTY
import com.lockwood.core.pagination.Pagination
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.themoviedb.movies.domain.model.MovieItem
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType
import com.lockwood.themoviedb.movies.presentation.ui.search.SearchViewState
import javax.inject.Inject

data class FavoriteMoviesViewState(
    val movieName: String,
    val inputClicked: Boolean,
    val inputStarted: Boolean,
    val inputEmpty: Boolean,
    val movies: List<MovieItem>,
    val itemViewType: Int,
    override var pageCount: Int,
    override var currentPage: Int,
    override var perPage: Int
) : Pagination {

    companion object {

        val initialState
            get() = FavoriteMoviesViewState(
                movieName = String.EMPTY,
                itemViewType = MoviesItemViewType.ITEM_VIEW_TYPE_LIST,
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