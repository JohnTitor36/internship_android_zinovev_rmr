package com.lockwood.themoviedb.movies.di.module.search

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.DefaultMoviesRepository
import com.lockwood.themoviedb.movies.data.repository.MoviesRemote
import com.lockwood.themoviedb.movies.domain.repository.MoviesRepository
import com.lockwood.themoviedb.movies.presentation.ui.search.SearchViewModel
import com.lockwood.themoviedb.movies.remote.DefaultMoviesRemote
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class SearchModule {

    @Binds
    @FeatureScope
    abstract fun provideMoviesRepository(authentication: DefaultMoviesRepository): MoviesRepository

    @Binds
    @FeatureScope
    abstract fun provideMoviesRemote(movies: DefaultMoviesRemote): MoviesRemote

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel

}