package com.lockwood.themoviedb.movies.di.component.list

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.list.MoviesListFragment
import dagger.Component

@Component
@FeatureScope
interface MoviesListComponent : BaseFragmentComponent<MoviesListFragment> {

    @Component.Builder
    interface Builder {

        fun build(): MoviesListComponent

    }

}