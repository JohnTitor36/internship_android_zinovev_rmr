package com.lockwood.themoviedb.login.di.component

import com.lockwood.core.di.component.BaseActivityComponent
import com.lockwood.core.di.provider.ApplicationProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.di.module.LoginModule
import com.lockwood.themoviedb.login.presentation.LoginActivity
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [LoginModule::class]
)
@FeatureScope
interface LoginComponent : BaseActivityComponent<LoginActivity> {

    @Component.Builder
    interface Builder {

        fun applicationProvider(applicationProvider: ApplicationProvider): Builder

        fun build(): LoginComponent

    }

}