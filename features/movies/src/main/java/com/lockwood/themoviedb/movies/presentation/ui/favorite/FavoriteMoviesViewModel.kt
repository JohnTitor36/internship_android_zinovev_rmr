package com.lockwood.themoviedb.movies.presentation.ui.favorite

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.preferences.di.qualifier.AccountId
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.movies.domain.repository.FavoriteMoviesRepository
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    @AccountId private val accountId: String,
    private val searchMoviesRepository: FavoriteMoviesRepository,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    companion object {

        private const val DEFAULT_PAGE = 1
        private const val DEBOUNCE_IN_MILLISECONDS = 2500L
    }

    val liveState = MutableLiveData(FavoriteMoviesViewState.initialState)

    private var state: FavoriteMoviesViewState by liveState.delegate()

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

    fun onFirstLoadMovies() {
        onLoadMovies()
    }

    fun loadMoreMovies() {
        val nextPage = state.currentPage + 1
        onLoadMovies(nextPage)
    }

    fun openMovie(id: Int) {
        val direction = FavoriteMoviesFragmentDirections.openMovie(id)
        navigateTo(direction)
    }

    private fun onLoadMovies(page: Int = DEFAULT_PAGE) {
        searchMoviesRepository.loadFavoriteMovies(
            accountId = accountId,
            page = page
        )
            .debounce(DEBOUNCE_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            .schedulersIoToMain(schedulers)
            .subscribe(
                {
                    val startMovies = if (page != DEFAULT_PAGE) {
                        state.movies
                    } else {
                        emptyList()
                    }
                    Timber.d("movies: ${it.results}")
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