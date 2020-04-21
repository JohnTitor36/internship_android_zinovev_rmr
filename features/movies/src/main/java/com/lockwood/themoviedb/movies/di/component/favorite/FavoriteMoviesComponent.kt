package com.lockwood.themoviedb.movies.di.component.favorite

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.themoviedb.movies.di.module.favorite.FavoriteMoviesModule
import com.lockwood.themoviedb.movies.di.module.favorite.FavoriteMoviesServiceModule
import com.lockwood.themoviedb.movies.presentation.ui.favorite.FavoriteMoviesFragment
import dagger.Component

@Component(
    dependencies = [
        AppToolsProvider::class,
        NetworkToolsProvider::class,
        PreferencesToolsProvider::class
    ],
    modules = [
        FavoriteMoviesModule::class,
        FavoriteMoviesServiceModule::class
    ]
)
@FeatureScope
interface FavoriteMoviesComponent : BaseFragmentComponent<FavoriteMoviesFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun networkToolsProvider(networkToolsProvider: NetworkToolsProvider): Builder

        fun build(): FavoriteMoviesComponent

    }

}