package com.lockwood.core.network.extensions

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService

@Suppress("deprecation", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
val Context.hasInternetConnection: Boolean
    get() {
        val connectivityManager = getSystemService<ConnectivityManager>()
        return if (connectivityManager != null) {
            var isWifiConn = false
            var isMobileConn = false
            connectivityManager.allNetworks.forEach { network ->
                connectivityManager.getNetworkInfo(network).apply {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isWifiConn = isWifiConn || isConnected
                    }
                    if (type == ConnectivityManager.TYPE_MOBILE) {
                        isMobileConn = isMobileConn || isConnected
                    }
                }
            }
            isWifiConn || isMobileConn
        } else {
            false
        }
    }