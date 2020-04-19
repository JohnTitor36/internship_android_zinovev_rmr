package com.lockwood.core.network.manager

import com.lockwood.core.network.manager.network.NetworkConnectivityManager
import com.lockwood.core.network.manager.wifi.WifiConnectivityManager
import com.lockwood.core.network.manager.wifi.WifiSecurityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityManager @Inject constructor(
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val wiFiConnectivityManager: WifiConnectivityManager,
    private val wifiSecurityManager: WifiSecurityManager
) {

    val hasInternetConnection: Boolean
        get() {
            return networkConnectivityManager.hasInternetConnection
        }

    val wifiEnabled: Boolean
        get() {
            return wiFiConnectivityManager.isEnabled
        }

    val safeConnection: Boolean
        get() {
            return wifiSecurityManager.isSafeConnection()
        }

}