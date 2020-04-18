package com.lockwood.themoviedb.login.presentation.ui.pin.enter

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.LaunchActivityEvent
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import javax.inject.Inject

class PinEnterViewModel @Inject
constructor(
    private val userPreferences: UserPreferences,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    companion object {

        private const val MAIN_ACTIVITY_CLASS_NAME =
            "com.lockwood.themoviedb.presentation.ui.MainActivity"
    }

    val liveState: MutableLiveData<PinEnterViewState> =
        MutableLiveData(PinEnterViewState.initialState)

    private var state: PinEnterViewState by liveState.delegate()

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        val throwableMessage = throwable.message
        if (throwableMessage != null) {
            state = state.copy(errorMessage = throwableMessage)
        }
    }


    private fun handleWrongPinEntered() {

    }

    private fun handleRightPinEntered() {
        userPreferences.setUserLoggedIn(true)

        val clearFlags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val launchMainEvent = LaunchActivityEvent(MAIN_ACTIVITY_CLASS_NAME, clearFlags)
        eventsQueue.offer(launchMainEvent)
    }

}