package com.lockwood.themoviedb.movies.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.themoviedb.movies.di.module.MovieModule
import com.lockwood.themoviedb.movies.di.module.favorite.FavoriteMoviesRoomModule
import com.lockwood.themoviedb.movies.di.module.MovieServiceModule
import com.lockwood.themoviedb.movies.presentation.ui.MovieFragment
import dagger.Component

@Component(
    dependencies = [
        AppToolsProvider::class,
        NetworkToolsProvider::class,
        PreferencesToolsProvider::class
    ],
    modules = [
        MovieModule::class,
        MovieServiceModule::class
    ]
)
@FeatureScope
interface MovieComponent : BaseFragmentComponent<MovieFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun networkToolsProvider(networkToolsProvider: NetworkToolsProvider): Builder

        fun build(): MovieComponent

    }

}