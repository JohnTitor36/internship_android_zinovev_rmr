package com.lockwood.core.network.ui

import com.lockwood.core.event.Event
import com.lockwood.core.event.MessageEvent
import com.lockwood.core.network.R
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.core.ui.BaseViewModel

abstract class BaseNetworkViewModel(
    protected val apiKey: String,
    protected val resourceReader: ResourceReader,
    protected val connectivityManager: NetworkConnectivityManager,
    protected val schedulers: SchedulersProvider
) : BaseViewModel() {

    protected val noInternetEvent: Event
        get() {
            val noInternetMessage = resourceReader.string(R.string.title_no_network)
            return MessageEvent(noInternetMessage)
        }

    protected val Throwable.isNoInternetException: Boolean
        get() {
            return this is NoInternetConnectionException
        }

    protected fun checkHasInternet(
        onHasConnection: () -> Unit,
        onNoConnection: () -> Unit
    ) {
        if (connectivityManager.hasInternetConnection) {
            onHasConnection()
        } else {
            onNoConnection()
        }
    }

}