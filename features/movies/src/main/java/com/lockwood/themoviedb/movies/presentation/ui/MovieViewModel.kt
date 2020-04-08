package com.lockwood.themoviedb.movies.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    val liveState = MutableLiveData(MovieViewState.initialState)

    private var state: MovieViewState by liveState.delegate()

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

    fun onFavoriteClicked() {
        val isFavoriteMovie = !state.favoriteMovie
        state = state.copy(favoriteMovie = isFavoriteMovie)
    }

    fun onLoadMovie(id: Int) {
//                moviesRepository.searchMovies(name, page)
//            .debounce(DEBOUNCE_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
//            .schedulersIoToMain(schedulers)
//            .subscribe(
//                {
//                    val startMovies = if (page != DEFAULT_PAGE) {
//                        state.movies
//                    } else {
//                        emptyList()
//                    }
//                    val movies = startMovies + it.results
//                    state = state.copy(
//                        movies = movies,
//                        currentPage = it.page,
//                        pageCount = it.totalPages,
//                        perPage = if (it.totalPages > 0) {
//                            it.totalResults / it.totalPages
//                        } else {
//                            0
//                        }
//                    )
//                },
//                { handleError(it) }
//            )
//            .autoDispose()
    }

}