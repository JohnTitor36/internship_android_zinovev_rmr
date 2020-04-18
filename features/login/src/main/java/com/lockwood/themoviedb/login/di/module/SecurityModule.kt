package com.lockwood.themoviedb.login.di.module

import android.content.Context
import com.lockwood.core.di.scope.FeatureScope
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides

@Module
class SecurityModule {

    @Provides
    @FeatureScope
    fun provideAuthenticationService(context: Context): RootBeer {
        return RootBeer(context)
    }

}