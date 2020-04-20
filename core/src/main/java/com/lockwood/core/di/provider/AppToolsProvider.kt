package com.lockwood.core.di.provider

import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.router.LoginActivityRouter
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.core.window.WindowManager

interface AppToolsProvider : ApplicationProvider, SecurityToolsProvider {

    fun provideResourceReader(): ResourceReader

    fun provideWindowManager(): WindowManager

    fun provideLoginActivityRouter(): LoginActivityRouter

    fun provideSchedulers(): SchedulersProvider

}