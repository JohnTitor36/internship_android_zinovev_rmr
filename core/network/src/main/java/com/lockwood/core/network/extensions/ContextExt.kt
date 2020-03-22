package com.lockwood.core.network.extensions

import android.content.Context
import android.net.ConnectivityManager

@Suppress("deprecation", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
val Context.hasInternetConnection: Boolean
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
        return isWifiConn || isMobileConn
    }