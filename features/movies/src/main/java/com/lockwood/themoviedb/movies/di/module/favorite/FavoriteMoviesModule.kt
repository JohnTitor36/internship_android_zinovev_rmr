package com.lockwood.themoviedb.movies.di.module.favorite

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.cache.DefaultFavoriteMoviesCache
import com.lockwood.themoviedb.movies.data.DefaultFavoriteMoviesRepository
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesCache
import com.lockwood.themoviedb.movies.data.repository.FavoriteMoviesRemote
import com.lockwood.themoviedb.movies.domain.repository.FavoriteMoviesRepository
import com.lockwood.themoviedb.movies.presentation.ui.favorite.FavoriteMoviesViewModel
import com.lockwood.themoviedb.movies.remote.DefaultFavoriteMoviesRemote
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class FavoriteMoviesModule {

    @Binds
    @FeatureScope
    abstract fun provideFavoriteMoviesRepository(favoriteMoviesRepository: DefaultFavoriteMoviesRepository): FavoriteMoviesRepository

    @Binds
    @FeatureScope
    abstract fun provideFavoriteMoviesRemote(favoriteMoviesRemote: DefaultFavoriteMoviesRemote): FavoriteMoviesRemote

    @Binds
    @FeatureScope
    abstract fun provideFavoriteMoviesCache(favoriteMoviesCache: DefaultFavoriteMoviesCache): FavoriteMoviesCache

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(FavoriteMoviesViewModel::class)
    abstract fun provideMoviesListViewModel(viewModel: FavoriteMoviesViewModel): ViewModel

}