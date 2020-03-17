package com.lockwood.core.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@Suppress("deprecation", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
val Context.isHasInternetConnection: Boolean
    @SuppressLint("MissingPermission")
    get() {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isWifiConn = false
        var isMobileConn = false
        connMgr.allNetworks.forEach { network ->
            connMgr.getNetworkInfo(network).apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or isConnected
                }
                if (type == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = isMobileConn or isConnected
                }
            }
        }
        return isWifiConn or isMobileConn
    }

fun Context.color(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)

inline fun <reified T : Activity> Context.launchActivity(
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(Intent(intent).apply(init))
}

inline fun newIntent(
    context: Context,
    className: String,
    init: Intent.() -> Unit = {}
): Intent {
    val packageName = context.packageName
    val packageNameWithoutSuffix = packageName.removeSuffix(DEBUG_SUFFIX)
    val resultClassName = "$packageNameWithoutSuffix$className"

    return Intent().apply {
        setClassName(packageName, resultClassName)
        init()
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

const val DEBUG_SUFFIX = ".debug"