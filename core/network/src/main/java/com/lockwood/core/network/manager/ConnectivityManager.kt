package com.lockwood.core.network.manager

import com.lockwood.core.network.manager.network.NetworkConnectivityManager
import com.lockwood.core.network.manager.wifi.WifiConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityManager @Inject constructor(
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val wiFiConnectivityManager: WifiConnectivityManager
) {

    val hasInternetConnection: Boolean
        get() {
            return networkConnectivityManager.hasInternetConnection
        }

    val isWifiEnabled: Boolean
        get() {
            return wiFiConnectivityManager.isEnabled
        }

    val currentWifiSsid: String
        get() {
            return wiFiConnectivityManager.currentSsid
        }

    val currentWifiEncryptionType: String
        get() {
            return wiFiConnectivityManager.currentEncryptionType
        }

}