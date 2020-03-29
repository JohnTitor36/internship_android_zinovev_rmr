package com.lockwood.themoviedb.movies.di.module.favorite

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.favorite.FavoriteMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class FavoriteMoviesModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(FavoriteMoviesViewModel::class)
    abstract fun provideMoviesListViewModel(viewModel: FavoriteMoviesViewModel): ViewModel

}