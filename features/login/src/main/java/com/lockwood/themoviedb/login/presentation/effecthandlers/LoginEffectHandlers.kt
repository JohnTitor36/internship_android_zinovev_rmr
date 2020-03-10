package com.lockwood.themoviedb.login.presentation.effecthandlers

import com.lockwood.core.di.qualifier.MainThread
import com.lockwood.themoviedb.login.domain.LoginEffect
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.domain.NotifyLoginComplete
import com.lockwood.themoviedb.login.domain.NotifyLoginInvalidInfo
import com.lockwood.themoviedb.login.presentation.view.LoginViewActions
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import javax.inject.Inject

interface LoginEffectHandlers {

    fun createEffectHandlers(view: LoginViewActions): ObservableTransformer<LoginEffect, LoginEvent>
}

class DefaultLoginEffectHandlers @Inject constructor(
    @MainThread private val mainThread: Scheduler
) : LoginEffectHandlers {

    override fun createEffectHandlers(
        view: LoginViewActions
    ): ObservableTransformer<LoginEffect, LoginEvent> {

        return RxMobius.subtypeEffectHandler<LoginEffect, LoginEvent>()
            .addAction(
                NotifyLoginComplete::class.java,
                view::showLoginComplete,
                mainThread
            )
            .addAction(
                NotifyLoginInvalidInfo::class.java,
                view::showLoginInvalidInfo,
                mainThread
            )
            .build()
    }

}