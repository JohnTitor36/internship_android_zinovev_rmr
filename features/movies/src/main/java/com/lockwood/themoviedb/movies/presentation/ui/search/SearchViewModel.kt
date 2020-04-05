package com.lockwood.themoviedb.movies.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.livedata.mapDistinct
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.movies.domain.repository.MoviesRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    val liveState: MutableLiveData<SearchViewState> = MutableLiveData(SearchViewState.initialState)

    val movies = liveState.mapDistinct { it.movies }

    private var state: SearchViewState by liveState.delegate()

    // TDOO: Настроить softInpuMode под Snackbar
    override fun handleError(throwable: Throwable) {
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

    fun inputClicked() {
        if (!state.inputClicked) {
            state = state.copy(inputClicked = true)
        }
    }

    fun movieNameEntered(name: String) {
        checkIsInputStarted(name)
        onLoadMovies(name)
    }

    private fun checkIsInputStarted(name: String) {
        val inputStarted = if (!state.inputStarted) {
            name.isNotEmpty()
        } else {
            true
        }
        state = state.copy(inputStarted = inputStarted, movieName = name)
    }

    private fun onLoadMovies(name: String) {
        if (name.isEmpty()) {
            return
        }

        moviesRepository.searchMovies(name)
            .schedulersIoToMain(schedulers)
            .subscribe(
                {
                    val movies = it.results
                    state = state.copy(movies = movies)
                },
                { handleError(it) }
            )
            .autoDispose()
    }

}