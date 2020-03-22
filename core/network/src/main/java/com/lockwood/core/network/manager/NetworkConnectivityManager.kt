package com.lockwood.core.network.manager

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectivityManager @Inject constructor(private val context: Context) {

    private val connectivityManager
        get() = context.getSystemService<ConnectivityManager>()

    @Suppress("deprecation", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    val hasInternetConnection: Boolean
        get() {
            val connectivityManager = connectivityManager
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

}