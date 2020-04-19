package com.lockwood.core.network.manager

import android.content.Context
import android.net.wifi.WifiManager
import androidx.core.content.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkSecurityChecker @Inject constructor(
    private val context: Context,
    private val networkConnectivityManager: NetworkConnectivityManager
) {

    companion object {

        private const val NOT_SAFE_WI_FI_NAME = "free"
    }

    private val wifiManager
        get() = context.getSystemService<WifiManager>()

    fun isSafeConnection(): Boolean {
        val safeSsid = isSafeSsid()
        return safeSsid
    }

    private fun isSafeSsid(): Boolean {
        val currentSsid = networkConnectivityManager.currentSsid
        return if (currentSsid.isEmpty()) {
            true
        } else {
            currentSsid.contains(NOT_SAFE_WI_FI_NAME, true)
        }
    }

}