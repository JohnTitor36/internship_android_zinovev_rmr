package com.lockwood.themoviedb.login.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.provider.ApplicationProvider
import com.lockwood.core.di.provider.UiToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.provider.PreferencesApiProvider
import com.lockwood.themoviedb.login.di.module.LoginModule
import com.lockwood.themoviedb.login.presentation.ui.LoginFragment
import dagger.Component

@Component(
    dependencies = [
        AppToolsProvider::class,
        NetworkToolsProvider::class,
        PreferencesApiProvider::class
    ],
    modules = [
        LoginModule::class
    ]
)
@FeatureScope
interface LoginComponent : BaseFragmentComponent<LoginFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun networkToolsProvider(networkToolsProvider: NetworkToolsProvider): Builder

        fun preferencesApiProvider(preferencesApiProvider: PreferencesApiProvider): Builder

        fun build(): LoginComponent

    }

}