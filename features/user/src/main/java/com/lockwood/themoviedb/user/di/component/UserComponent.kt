package com.lockwood.themoviedb.user.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.themoviedb.user.di.module.ServiceModule
import com.lockwood.themoviedb.user.di.module.UserModule
import com.lockwood.themoviedb.user.presentation.ui.UserFragment
import dagger.Component

@Component(
    dependencies = [
        AppToolsProvider::class,
        NetworkToolsProvider::class,
        PreferencesToolsProvider::class
    ],
    modules = [
        UserModule::class,
        ServiceModule::class
    ]
)
@FeatureScope
interface UserComponent : BaseFragmentComponent<UserFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun networkToolsProvider(networkToolsProvider: NetworkToolsProvider): Builder

        fun build(): UserComponent

    }

}