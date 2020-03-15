package com.lockwood.core.di

import android.content.Context
import com.lockwood.core.di.provider.ApplicationProvider

interface DaggerApplication {

    fun getApplicationContext(): Context

    fun getApplicationProvider(): ApplicationProvider

}