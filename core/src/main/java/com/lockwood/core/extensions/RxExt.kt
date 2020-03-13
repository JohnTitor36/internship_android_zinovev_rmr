package com.lockwood.core.extensions

import com.lockwood.core.domain.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Single<T>.schedulersIoToMain(provider: SchedulersProvider): Single<T> {
    return subscribeOn(provider.io()).observeOn(provider.mainThread())
}

fun <T> Observable<T>.schedulersIoToMain(provider: SchedulersProvider): Observable<T> {
    return subscribeOn(provider.io()).observeOn(provider.mainThread())
}

fun Completable.schedulersIoToMain(provider: SchedulersProvider): Completable {
    return subscribeOn(provider.io()).observeOn(provider.mainThread())
}