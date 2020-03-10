package com.lockwood.themoviedb.login.di

import com.lockwood.core.di.BaseActivityComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.presentation.LoginActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [LoginActivityModule::class])
@FeatureScope
interface LoginComponent : BaseActivityComponent<LoginActivity> {

    @Component.Builder
    interface Builder {

        fun build(): LoginComponent

        @BindsInstance
        fun activity(activity: LoginActivity): Builder

        fun loginActivityModule(module: LoginActivityModule): Builder

    }

}