package com.lockwood.core.extensions

import androidx.fragment.app.Fragment
import com.lockwood.core.di.provider.AppToolsProvider

val Fragment.appToolsProvider: AppToolsProvider
    get() = requireActivity().appToolsProvider