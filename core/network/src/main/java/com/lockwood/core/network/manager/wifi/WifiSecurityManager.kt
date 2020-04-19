package com.lockwood.core.network.manager.wifi

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WifiSecurityManager @Inject constructor(
    private val wifiConnectivityManager: WifiConnectivityManager
) {

    companion object {

        private const val WIFI_SECURITY_TYPE_NONE = "NONE"
        private const val WIFI_SECURITY_TYPE_WPA = "WPA"
        private const val WIFI_SECURITY_TYPE_WEP = "WEP"

        private const val NOT_SAFE_WI_FI_NAME = "free"
    }

    fun isSafeConnection(): Boolean {
        val safeSsid = isSafeSsid()
        val safeSecurityType = isSafeSecurityType()
        return safeSsid && safeSecurityType
    }

    private fun isSafeSsid(): Boolean {
        val currentSsid = wifiConnectivityManager.currentSsid
        return !currentSsid.contains(NOT_SAFE_WI_FI_NAME, true)
    }

    private fun isSafeSecurityType(): Boolean {
        val capabilities = wifiConnectivityManager.currentNetworkCapabilities
        return if (capabilities.isNullOrEmpty()) {
            true
        } else {
            val notSafeSecurityTypes = arrayOf(
                WIFI_SECURITY_TYPE_WEP,
                WIFI_SECURITY_TYPE_WPA,
                WIFI_SECURITY_TYPE_NONE
            )
            notSafeSecurityTypes.map { capabilities.contains(it) }.none { it }
        }
    }


}