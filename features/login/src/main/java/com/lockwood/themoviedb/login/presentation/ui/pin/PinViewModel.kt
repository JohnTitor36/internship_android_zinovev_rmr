package com.lockwood.themoviedb.login.presentation.ui.pin

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.BackPressEvent
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.pin.keyboard.listener.PinKeyboardListener
import com.lockwood.themoviedb.login.event.KeyboardClearEvent
import javax.inject.Inject

class PinViewModel @Inject
constructor(
    private val userPreferences: UserPreferences,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    val liveState: MutableLiveData<PinViewState> = MutableLiveData(PinViewState.initialState)

    val pinKeyboardListener = object : PinKeyboardListener {

        override fun onExitClick() {
            val event = BackPressEvent()
            eventsQueue.offer(event)
        }

        override fun onResetEnteredDigits() {
            val event = KeyboardClearEvent()
            eventsQueue.offer(event)
        }

        override fun onLastItemEntered(digits: String) {
            // TODO: запомнить веденные цифры и сравнить с введенными повторно
            // onResetEnteredDigits()
        }

        override fun onDigitClick(digit: Int) = Unit

        override fun onClearDigitClick() = Unit

    }

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

}