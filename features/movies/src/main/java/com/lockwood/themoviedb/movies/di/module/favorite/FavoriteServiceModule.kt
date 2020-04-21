package com.lockwood.themoviedb.movies.di.module.favorite

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.themoviedb.movies.remote.service.FavoriteMoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FavoriteServiceModule {

    @Provides
    @FeatureScope
    fun provideFavoriteServiceModule(@AuthRetrofit retrofit: Retrofit.Builder): FavoriteMoviesService {
        return retrofit.build().create(FavoriteMoviesService::class.java)
    }

}