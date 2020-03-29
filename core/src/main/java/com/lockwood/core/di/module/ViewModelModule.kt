package com.lockwood.core.di.module

import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}