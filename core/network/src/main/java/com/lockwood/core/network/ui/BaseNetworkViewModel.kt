package com.lockwood.core.network.ui

import com.lockwood.core.event.Event
import com.lockwood.core.event.MessageEvent
import com.lockwood.core.network.R
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.core.ui.BaseViewModel

abstract class BaseNetworkViewModel(
    protected val resourceReader: ResourceReader,
    protected val schedulers: SchedulersProvider
) : BaseViewModel() {

    protected val noInternetEvent: Event
        get() {
            val noInternetMessage = resourceReader.string(R.string.title_check_network_connection)
            return MessageEvent(noInternetMessage)
        }

    protected val Throwable.isNoInternetException: Boolean
        get() {
            return this is NoInternetConnectionException
        }

}