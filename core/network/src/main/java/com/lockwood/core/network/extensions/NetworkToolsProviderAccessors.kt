package com.lockwood.core.network.extensions

import android.app.Activity
import androidx.fragment.app.Fragment
import com.lockwood.core.network.di.DaggerNetworkApplication
import com.lockwood.core.network.di.provider.NetworkToolsProvider

val Activity.networkToolsProvider: NetworkToolsProvider
    get() = (applicationContext as DaggerNetworkApplication).getNetworkToolsProvider()

val Fragment.networkToolsProvider: NetworkToolsProvider
    get() = requireActivity().networkToolsProvider