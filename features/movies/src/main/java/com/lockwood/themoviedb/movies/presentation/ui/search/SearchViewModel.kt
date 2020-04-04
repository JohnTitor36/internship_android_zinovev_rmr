package com.lockwood.themoviedb.movies.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.event.EventsQueue
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.livedata.mapDistinct
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.movies.domain.model.Movie
import com.lockwood.themoviedb.movies.domain.repository.MoviesRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class SearchViewState(
    val movieName: String,
    val movieItemType: Int,
    val inputClicked: Boolean,
    val inputStarted: Boolean,
    val movies: List<Movie>
)

class SearchViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @ApiKey apiKey: String,
    resourceReader: ResourceReader,
    connectivityManager: NetworkConnectivityManager,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(apiKey, resourceReader, connectivityManager, schedulers) {

    val liveState: MutableLiveData<SearchViewState> = MutableLiveData(createInitialState())

    val movies = liveState.mapDistinct { it.movies }

    val eventsQueue by lazy { EventsQueue() }

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
        loadMovies(name)
    }

    private fun createInitialState(): SearchViewState {
        return SearchViewState("", 0, false, false, emptyList())
    }

    private fun checkIsInputStarted(name: String) {
        val inputStarted = if (!state.inputStarted) {
            name.isNotEmpty()
        } else {
            true
        }
        state = state.copy(inputStarted = inputStarted, movieName = name)
    }

    private fun loadMovies(name: String) {
        if (name.isEmpty()) {
            return
        }

        checkHasInternet(
            onHasConnection = {
                moviesRepository.searchMovies(apiKey, name)
                    .schedulersIoToMain(schedulers)
                    .subscribe(
                        {
                            val movies = it.results
                            state = state.copy(movies = movies)
                        },
                        { handleError(it) }
                    )
                    .autoDispose()
            },
            onNoConnection = { eventsQueue.offer(noInternetEvent) }
        )
    }


}