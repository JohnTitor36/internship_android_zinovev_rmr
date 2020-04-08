package com.lockwood.themoviedb.movies.di.module.search

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.themoviedb.movies.remote.MoviesSearchService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchServiceModule {

    @Provides
    @FeatureScope
    fun provideMoviesSearchService(@AuthRetrofit retrofit: Retrofit.Builder): MoviesSearchService {
        return retrofit.build().create(MoviesSearchService::class.java)
    }

}