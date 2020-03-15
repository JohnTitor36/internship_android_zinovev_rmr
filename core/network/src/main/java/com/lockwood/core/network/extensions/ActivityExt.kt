package com.lockwood.core.network.extensions

import android.app.Activity
import com.lockwood.core.network.di.DaggerNetworkApplication
import com.lockwood.core.network.di.provider.NetworkToolsProvider

val Activity.networkToolsProvider: NetworkToolsProvider
    get() = (applicationContext as DaggerNetworkApplication).getNetworkToolsProvider()