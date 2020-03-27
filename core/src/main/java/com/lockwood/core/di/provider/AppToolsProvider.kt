package com.lockwood.core.di.provider

import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.router.LoginActivityRouter
import com.lockwood.core.schedulers.SchedulersProvider

interface AppToolsProvider : ApplicationProvider {

    fun provideResourceReader(): ResourceReader

    fun provideLoginActivityRouter(): LoginActivityRouter

    fun provideSchedulers(): SchedulersProvider

}