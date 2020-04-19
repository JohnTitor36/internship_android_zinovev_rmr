package com.lockwood.core.network.manager.wifi

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.core.content.getSystemService
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WifiConnectivityManager @Inject constructor(
    private val context: Context
) {

    private val wifiManager
        get() = requireNotNull(context.getSystemService<WifiManager>())

    val isEnabled: Boolean
        get() {
            return wifiManager.isWifiEnabled
        }

    val currentSsid: String
        get() {
            val ssid = wifiManager.connectionInfo.ssid
            return ssid.replace("\"", "")
        }

    val currentNetworkCapabilities: String?
        get() {
            return findNetworkCapabilities(currentSsid)
        }

    private fun findNetworkCapabilities(ssid: String): String? {
        val networkList: List<ScanResult> = wifiManager.scanResults
        return networkList.firstOrNull { it.SSID == ssid }?.capabilities
    }

}