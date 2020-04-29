package com.lockwood.themoviedb.movies.di.module.search

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.themoviedb.movies.remote.service.SearchMoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchServiceModule {

    @Provides
    @FeatureScope
    fun provideMoviesSearchService(
        @AuthRetrofit retrofit: Retrofit.Builder
    ): SearchMoviesService {
        return retrofit.build().create(SearchMoviesService::class.java)
    }

}