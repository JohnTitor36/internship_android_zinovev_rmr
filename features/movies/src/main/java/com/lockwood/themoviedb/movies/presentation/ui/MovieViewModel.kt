package com.lockwood.themoviedb.movies.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    val liveState = MutableLiveData(MovieViewState.initialState)

    private var state: MovieViewState by liveState.delegate()

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        state = state.copy(loading = false)
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
        state.copy(movieId = id)
        movieRepository.loadMovie(id)
            .schedulersIoToMain(schedulers)
            .subscribe(
                { state = state.copy(movie = it, loading = false) },
                { handleError(it) }
            )
            .autoDispose()
    }

}