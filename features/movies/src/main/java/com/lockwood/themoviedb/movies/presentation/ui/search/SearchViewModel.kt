package com.lockwood.themoviedb.movies.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.livedata.delegate
import com.lockwood.core.ui.BaseViewModel
import javax.inject.Inject

data class SearchViewState(
    val movieName: String,
    val movieItemType: Int,
    val inputStarted: Boolean
)

class SearchViewModel @Inject constructor() : BaseViewModel() {

    val liveState: MutableLiveData<SearchViewState> = MutableLiveData(createInitialState())
    private var state: SearchViewState by liveState.delegate()

    private fun createInitialState(): SearchViewState {
        return SearchViewState("", 0, false)
    }

    fun inputStarted() {
        if (!state.inputStarted) {
            state = state.copy(inputStarted = true)
        }
    }

    fun movieNameEntered(name: String) {
        state = state.copy(movieName = name)
    }

}