package com.lockwood.themoviedb.movies.di.module.favorite

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthHttpClient
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.core.network.di.qualifier.SessionInterceptor
import com.lockwood.core.network.extensions.edit
import com.lockwood.themoviedb.movies.remote.service.FavoriteMoviesService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class FavoriteMoviesServiceModule {

    @Provides
    @FeatureScope
    fun provideFavoriteServiceModule(
        @AuthRetrofit retrofit: Retrofit.Builder,
        @AuthHttpClient okHttpClient: OkHttpClient,
        @SessionInterceptor sessionInterceptor: Interceptor
    ): FavoriteMoviesService {
        val client = okHttpClient.edit {
            addInterceptor(sessionInterceptor)
        }
        return retrofit.client(client).build().create(FavoriteMoviesService::class.java)
    }

}