package com.lockwood.core.network.di

import com.lockwood.core.network.di.provider.NetworkToolsProvider

interface DaggerNetworkApplication {

    fun getNetworkToolsProvider(): NetworkToolsProvider

}