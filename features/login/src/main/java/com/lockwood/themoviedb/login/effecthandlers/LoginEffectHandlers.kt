package com.lockwood.themoviedb.login.effecthandlers

import android.content.Context
import com.lockwood.themoviedb.login.domain.LoginEffect
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.view.LoginViewActions

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer

object LoginEffectHandlers {

    fun createEffectHandlers(
        view: LoginViewActions,
        context: Context
    ): ObservableTransformer<LoginEffect, LoginEvent> {
        return RxMobius.subtypeEffectHandler<LoginEffect, LoginEvent>().build()
    }

}