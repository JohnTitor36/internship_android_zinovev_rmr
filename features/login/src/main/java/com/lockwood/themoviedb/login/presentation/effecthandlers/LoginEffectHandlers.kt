package com.lockwood.themoviedb.login.presentation.effecthandlers

import com.lockwood.themoviedb.login.domain.LoginEffect
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.domain.NotifyLoginComplete
import com.lockwood.themoviedb.login.domain.NotifyLoginInvalidInfo
import com.lockwood.themoviedb.login.presentation.view.LoginViewActions

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

object LoginEffectHandlers {

    fun createEffectHandlers(
        view: LoginViewActions
    ): ObservableTransformer<LoginEffect, LoginEvent> {
        return RxMobius.subtypeEffectHandler<LoginEffect, LoginEvent>()
            .addAction(
                NotifyLoginComplete::class.java,
                view::showLoginComplete,
                AndroidSchedulers.mainThread()
            )
            .addAction(
                NotifyLoginInvalidInfo::class.java,
                view::showLoginInvalidInfo,
                AndroidSchedulers.mainThread()
            )
            .build()
    }

}