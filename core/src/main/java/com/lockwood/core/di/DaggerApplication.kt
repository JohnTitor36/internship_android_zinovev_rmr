package com.lockwood.core.di

import android.content.Context
import com.lockwood.core.di.provider.AppToolsProvider

interface DaggerApplication {

    fun getApplicationContext(): Context

    fun getAppToolsProvider(): AppToolsProvider

}