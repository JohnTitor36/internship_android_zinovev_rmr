package com.lockwood.core.extensions

import android.app.Activity
import androidx.fragment.app.Fragment
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.provider.AppToolsProvider

val Activity.appToolsProvider: AppToolsProvider
    get() = (applicationContext as DaggerApplication).getAppToolsProvider()

val Fragment.appToolsProvider: AppToolsProvider
    get() = requireActivity().appToolsProvider