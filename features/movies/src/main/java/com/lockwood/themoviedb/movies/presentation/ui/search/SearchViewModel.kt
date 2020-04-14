package com.lockwood.themoviedb.movies.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.movies.domain.repository.SearchMoviesRepository
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType.ITEM_VIEW_TYPE_GRID
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType.ITEM_VIEW_TYPE_LIST
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchMoviesRepository: SearchMoviesRepository,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    companion object {

        private const val DEFAULT_PAGE = 1
        private const val DEBOUNCE_IN_MILLISECONDS = 2500L
    }

    val liveState = MutableLiveData(SearchViewState.initialState)

    private var state: SearchViewState by liveState.delegate()

    // TDOO: Настроить softInpuMode под Snackbar
    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        if (throwable.isNoInternetException) {
            eventsQueue.offer(noInternetEvent)
        } else {
            val throwableMessage = throwable.message
            if (throwableMessage != null) {
                val messageEvent = ErrorMessageEvent(throwableMessage)
                eventsQueue.offer(messageEvent)
            }
        }
    }

    fun onInputClicked() {
        if (!state.inputClicked) {
            state = state.copy(inputClicked = true)
        }
    }

    fun onMovieNameEntered(name: String) {
        resetPagination()
        checkIsInputStarted(name)
        onLoadMovies(name)
    }

    fun onChangeMoviesViewType() {
        val newViewType = if (state.itemViewType == ITEM_VIEW_TYPE_LIST) {
            ITEM_VIEW_TYPE_GRID
        } else {
            ITEM_VIEW_TYPE_LIST
        }
        state = state.copy(itemViewType = newViewType)
    }

    fun loadMoreMovies() {
        val nextPage = state.currentPage + 1
        onLoadMovies(state.movieName, nextPage)
    }

    fun openMovie(id: Int) {
        val direction = SearchFragmentDirections.openMovie(id)
        navigateTo(direction)
    }

    private fun resetPagination() {
        state = state.copy(currentPage = DEFAULT_PAGE)
    }

    private fun checkIsInputStarted(name: String) {
        val inputStarted = if (!state.inputStarted) {
            name.isNotEmpty()
        } else {
            true
        }
        state = state.copy(
            inputStarted = inputStarted,
            inputEmpty = name.isEmpty(),
            movieName = name
        )
    }

    private fun onLoadMovies(name: String, page: Int = DEFAULT_PAGE) {
        if (name.isEmpty()) {
            return
        }

        searchMoviesRepository.searchMovies(name, page)
            .debounce(DEBOUNCE_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            .schedulersIoToMain(schedulers)
            .subscribe(
                {
                    val startMovies = if (page != DEFAULT_PAGE) {
                        state.movies
                    } else {
                        emptyList()
                    }
                    val movies = startMovies + it.results
                    state = state.copy(
                        movies = movies,
                        currentPage = it.page,
                        pageCount = it.totalPages,
                        perPage = if (it.totalPages > 0) {
                            it.totalResults / it.totalPages
                        } else {
                            0
                        }
                    )
                },
                { handleError(it) }
            )
            .autoDispose()
    }

}