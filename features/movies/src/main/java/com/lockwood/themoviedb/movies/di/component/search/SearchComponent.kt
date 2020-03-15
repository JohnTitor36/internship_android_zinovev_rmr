package com.lockwood.themoviedb.movies.di.component.search

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.search.SearchFragment
import dagger.Component

@Component
@FeatureScope
interface SearchComponent : BaseFragmentComponent<SearchFragment> {

    @Component.Builder
    interface Builder {

        fun build(): SearchComponent

    }

}