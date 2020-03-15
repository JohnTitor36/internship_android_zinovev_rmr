package com.lockwood.themoviedb.movies.di.component.favorite

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.favorite.FavoriteMoviesFragment
import dagger.Component

@Component
@FeatureScope
interface FavoriteMoviesComponent : BaseFragmentComponent<FavoriteMoviesFragment> {

    @Component.Builder
    interface Builder {

        fun build(): FavoriteMoviesComponent

    }

}