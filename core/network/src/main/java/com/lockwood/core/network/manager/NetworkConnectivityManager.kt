package com.lockwood.core.network.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.core.content.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityManager @Inject constructor(
    private val context: Context
) {

    private val connectivityManager
        get() = context.getSystemService<ConnectivityManager>()

    private val wifiManager
        get() = context.getSystemService<WifiManager>()

    @Suppress("deprecation")
    val hasInternetConnection: Boolean
        get() {
            val connectivityManager = connectivityManager
            return if (connectivityManager != null) {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                activeNetworkInfo != null && activeNetworkInfo.isConnected
            } else {
                false
            }
        }

    val currentSsid: String
        get() {
            val wifiManager = wifiManager
            return if (wifiManager != null) {
                val info = wifiManager.connectionInfo
                info.ssid
            } else {
                ""
            }
        }

}