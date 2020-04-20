package com.lockwood.core.di

import android.content.Context
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.provider.SecurityToolsProvider

interface DaggerApplication {

    fun getApplicationContext(): Context

    fun getAppToolsProvider(): AppToolsProvider

    fun getSecurityToolsProvider(): SecurityToolsProvider

}