package com.lockwood.themoviedb.login

import com.lockwood.themoviedb.login.data.LoginInfo
import com.lockwood.themoviedb.login.domain.LoginEffect
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.domain.LoginUpdate
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.MobiusLoop.Controller
import com.spotify.mobius.android.AndroidLogger
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer

object LoginInjector {

    private const val TAG = "Login"

    fun createController(
        effectHandlers: ObservableTransformer<LoginEffect, LoginEvent>, defaultModel: LoginInfo
    ): Controller<LoginInfo, LoginEvent> {
        return MobiusAndroid.controller(createLoop(effectHandlers), defaultModel)
    }

    private fun createLoop(
        effectHandlers: ObservableTransformer<LoginEffect, LoginEvent>
    ): MobiusLoop.Factory<LoginInfo, LoginEvent, LoginEffect> {
        return RxMobius
            .loop(LoginUpdate(), effectHandlers)
            .logger(AndroidLogger.tag(TAG))
    }

}