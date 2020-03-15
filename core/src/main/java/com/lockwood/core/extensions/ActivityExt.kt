package com.lockwood.core.extensions

import android.app.Activity
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.provider.ApplicationProvider

// Чтобы не было излишним в BaseActivity решил вынести в ext
val Activity.applicationProvider: ApplicationProvider
    get() = (applicationContext as DaggerApplication).getApplicationProvider()