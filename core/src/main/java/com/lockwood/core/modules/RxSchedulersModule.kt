package com.lockwood.core.modules

import com.lockwood.core.di.qualifier.Io
import com.lockwood.core.di.qualifier.MainThread
import com.lockwood.core.di.scope.FeatureScope
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class RxSchedulersModule {

    @Provides
    @FeatureScope
    @MainThread
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @FeatureScope
    @Io
    fun provideIoScheduler(): Scheduler = Schedulers.io()

}