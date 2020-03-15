package com.lockwood.themoviedb.movies.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.SearchFragment
import dagger.Component

@Component(
    dependencies = [AppToolsProvider::class]
)
@FeatureScope
interface SearchComponent : BaseFragmentComponent<SearchFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun build(): SearchComponent

    }

}