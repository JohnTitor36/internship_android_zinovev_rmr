package com.lockwood.themoviedb.movies.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.event.EventsQueue
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.movies.domain.repository.MoviesRepository
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class SearchViewState(
    val movieName: String,
    val movieItemType: Int,
    val inputClicked: Boolean,
    val inputStarted: Boolean
)

class SearchViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @ApiKey apiKey: String,
    resourceReader: ResourceReader,
    connectivityManager: NetworkConnectivityManager,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(apiKey, resourceReader, connectivityManager, schedulers) {

    companion object {

        private const val MOVIES_SEARCH_DEBOUNCE = 1L
    }

    val liveState: MutableLiveData<SearchViewState> = MutableLiveData(createInitialState())

    val eventsQueue by lazy { EventsQueue() }

    private var state: SearchViewState by liveState.delegate()

    override fun handleError(throwable: Throwable) {
        if (throwable is NoInternetConnectionException) {
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
        return SearchViewState("", 0, false, false)
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

        moviesRepository.searchMovies(apiKey, name)
            .debounce(MOVIES_SEARCH_DEBOUNCE, TimeUnit.SECONDS)
            .schedulersIoToMain(schedulers)
            .subscribe(
                { Timber.d("data: ${it.results.firstOrNull()}") },
                { handleError(it) }
            )
            .autoDispose()
    }


}