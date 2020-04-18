package com.lockwood.themoviedb.login.di.module

import android.content.Context
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.remote.AuthenticationService
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class SecurityModule {

    @Provides
    @FeatureScope
    fun provideAuthenticationService(context: Context): RootBeer {
        return RootBeer(context)
    }

}