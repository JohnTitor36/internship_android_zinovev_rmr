package com.lockwood.core.extensions

import android.app.Activity
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.provider.AppToolsProvider

// Чтобы не было излишним в BaseActivity решил вынести в ext
val Activity.appToolsProvider: AppToolsProvider
    get() = (applicationContext as DaggerApplication).getAppToolsProvider()