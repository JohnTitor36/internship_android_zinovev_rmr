package com.lockwood.core.network.manager

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkSecurityManager @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {

    companion object {

        private const val NOT_SAFE_WI_FI_NAME = "free"
    }

    fun isSafeConnection(): Boolean {
        val safeSsid = isSafeSsid()
        return safeSsid
    }

    private fun isSafeSsid(): Boolean {
        val currentSsid = connectivityManager.currentWifiSsid
        return if (currentSsid.isEmpty()) {
            true
        } else {
            currentSsid.contains(NOT_SAFE_WI_FI_NAME, true)
        }
    }

}