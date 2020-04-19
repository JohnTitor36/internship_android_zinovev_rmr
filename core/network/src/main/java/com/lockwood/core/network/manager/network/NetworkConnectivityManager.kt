package com.lockwood.core.network.manager.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityManager @Inject constructor(
    private val context: Context
) {

    private val connectivityManager
        get() = requireNotNull(context.getSystemService<ConnectivityManager>())

    @Suppress("deprecation")
    val hasInternetConnection: Boolean
        get() {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

}