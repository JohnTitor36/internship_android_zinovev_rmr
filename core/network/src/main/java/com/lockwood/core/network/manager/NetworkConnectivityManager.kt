package com.lockwood.core.network.manager

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityManager @Inject constructor(private val context: Context) {

    private val connectivityManager
        get() = context.getSystemService<ConnectivityManager>()

    @Suppress("deprecation")
    val hasInternetConnection: Boolean
        get() {
            val connectivityManager = connectivityManager
            return if (connectivityManager != null) {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return activeNetworkInfo != null && activeNetworkInfo.isConnected
            } else {
                false
            }
        }

}