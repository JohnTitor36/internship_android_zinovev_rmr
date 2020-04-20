package com.lockwood.core.di.provider

import com.lockwood.core.cryptographic.Cryptographer
import com.scottyab.rootbeer.RootBeer

interface SecurityToolsProvider : ApplicationProvider {

    fun provideRootBeer(): RootBeer

    fun provideCryptographer(): Cryptographer

}