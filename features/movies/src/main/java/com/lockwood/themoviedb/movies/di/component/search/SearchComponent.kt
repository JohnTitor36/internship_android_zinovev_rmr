package com.lockwood.themoviedb.movies.di.component.search

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.themoviedb.movies.di.module.search.SearchModule
import com.lockwood.themoviedb.movies.di.module.search.SearchServiceModule
import com.lockwood.themoviedb.movies.presentation.ui.search.SearchFragment
import dagger.Component

@Component(
    dependencies = [
        AppToolsProvider::class,
        NetworkToolsProvider::class,
        PreferencesToolsProvider::class
    ],
    modules = [
        SearchModule::class,
        SearchServiceModule::class
    ]
)
@FeatureScope
interface SearchComponent : BaseFragmentComponent<SearchFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun networkToolsProvider(networkToolsProvider: NetworkToolsProvider): Builder

        fun build(): SearchComponent

    }

}