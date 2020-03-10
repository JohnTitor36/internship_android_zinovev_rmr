package com.lockwood.themoviedb.login.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.presentation.effecthandlers.DefaultLoginEffectHandlers
import com.lockwood.themoviedb.login.presentation.effecthandlers.LoginEffectHandlers
import com.lockwood.themoviedb.login.presentation.view.DefaultLoginViewDataMapper
import com.lockwood.themoviedb.login.presentation.view.LoginViewDataMapper
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationBindingModule {

    @Binds
    @FeatureScope
    abstract fun bindLoginEffectHandlers(loginEffectHandlers: DefaultLoginEffectHandlers): LoginEffectHandlers

    @Binds
    @FeatureScope
    abstract fun bindLoginViewDataMappers(loginViewDataMapper: DefaultLoginViewDataMapper): LoginViewDataMapper

}