package com.lockwood.core.network.extensions

import androidx.fragment.app.Fragment
import com.lockwood.core.network.di.provider.NetworkToolsProvider

val Fragment.networkToolsProvider: NetworkToolsProvider
    get() = requireActivity().networkToolsProvider