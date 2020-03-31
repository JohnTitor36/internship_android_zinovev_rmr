package com.lockwood.themoviedb.movies.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.livedata.delegate
import com.lockwood.core.ui.BaseViewModel
import javax.inject.Inject

data class SearchViewState(
    val movieName: String,
    val movieItemType: Int,
    val loading: Boolean,
    val inputClicked: Boolean,
    val inputStarted: Boolean
)

class SearchViewModel @Inject constructor() : BaseViewModel() {

    val liveState: MutableLiveData<SearchViewState> = MutableLiveData(createInitialState())
    private var state: SearchViewState by liveState.delegate()

    private fun createInitialState(): SearchViewState {
        return SearchViewState("", 0, false, false, false)
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
    }


}