package com.lockwood.themoviedb.login.di.component

import com.lockwood.core.di.component.BaseActivityComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.di.module.LoginActivityModule
import com.lockwood.themoviedb.login.presentation.LoginActivity
import dagger.Component

@Component(modules = [LoginActivityModule::class])
@FeatureScope
interface LoginComponent : BaseActivityComponent<LoginActivity> {

    @Component.Builder
    interface Builder {

        fun build(): LoginComponent

        fun loginActivityModule(module: LoginActivityModule): Builder

    }

}