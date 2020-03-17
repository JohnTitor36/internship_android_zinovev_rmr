package com.lockwood.themoviedb.di.component

import com.lockwood.core.di.component.BaseActivityComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.themoviedb.presentation.ui.MainActivity
import dagger.Component

@Component(dependencies = [PreferencesToolsProvider::class])
@FeatureScope
interface MainComponent : BaseActivityComponent<MainActivity> {

    @Component.Builder
    interface Builder {

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun build(): MainComponent

    }
}