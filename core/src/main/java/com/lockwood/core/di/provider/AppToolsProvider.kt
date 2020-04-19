package com.lockwood.core.di.provider

import com.lockwood.core.cryptographic.Cryptographer
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.router.LoginActivityRouter
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.core.window.WindowManager
import com.scottyab.rootbeer.RootBeer

interface AppToolsProvider : ApplicationProvider {

    fun provideResourceReader(): ResourceReader

    fun provideWindowManager(): WindowManager

    fun provideLoginActivityRouter(): LoginActivityRouter

    fun provideSchedulers(): SchedulersProvider

    fun provideRootBeer(): RootBeer

    fun provideCryptographer(): Cryptographer

}