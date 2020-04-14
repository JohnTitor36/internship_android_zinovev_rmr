package com.lockwood.themoviedb.login.presentation.ui.pin

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.LaunchActivityEvent
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import javax.inject.Inject

class PinViewModel @Inject
constructor(
    private val userPreferences: UserPreferences,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    val liveState: MutableLiveData<PinViewState> = MutableLiveData(PinViewState.initialState)

    private var state: PinViewState by liveState.delegate()

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        if (throwable.isNoInternetException) {
            eventsQueue.offer(noInternetEvent)
        } else {
            val throwableMessage = throwable.message
            if (throwableMessage != null) {
                state = state.copy(errorMessage = throwableMessage)
            }
        }
    }

    private fun handleWrongPinEntered() {

    }

    private fun handleRightPinEntered() {

    }

}