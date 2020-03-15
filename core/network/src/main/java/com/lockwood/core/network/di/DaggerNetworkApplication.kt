package com.lockwood.core.network.di

import android.content.Context
import com.lockwood.core.network.di.provider.NetworkToolsProvider

interface DaggerNetworkApplication {

    fun getApplicationContext(): Context

    fun getNetworkToolsProvider(): NetworkToolsProvider

}