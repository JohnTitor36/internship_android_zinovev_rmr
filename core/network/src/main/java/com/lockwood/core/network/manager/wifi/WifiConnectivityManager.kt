package com.lockwood.core.network.manager.wifi

import android.content.Context
import android.net.wifi.WifiManager
import androidx.core.content.getSystemService
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
            return wifiManager.connectionInfo.ssid
        }

    val currentEncryptionType: String
        get() {
            return wifiManager.wifiState.toString()
        }

}