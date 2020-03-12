package com.lockwood.themoviedb.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.toaster.Toaster
import com.lockwood.core.ui.BaseMobiusFragment
import com.lockwood.themoviedb.login.data.LoginInfo
import com.lockwood.themoviedb.login.domain.LoginEffect
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.domain.LoginInfoBundlePacker
import com.lockwood.themoviedb.login.presentation.effecthandlers.LoginEffectHandlers
import com.lockwood.themoviedb.login.presentation.view.LoginViewData
import com.lockwood.themoviedb.login.presentation.view.LoginViewDataMapper
import com.lockwood.themoviedb.login.presentation.view.LoginViews
import com.spotify.mobius.extras.Connectables.contramap
import com.spotify.mobius.functions.Function
import javax.inject.Inject

class LoginFragment @Inject constructor(
    @FeatureScope private val toaster: Toaster,
    private val loginViewDataMapper: LoginViewDataMapper,
    private val loginEffectHandlers: LoginEffectHandlers
) : BaseMobiusFragment<LoginInfo, LoginEvent, LoginEffect>() {

    companion object {

        private const val ARGUMENT_LOGIN_INFO = "ARGUMENT_LOGIN_INFO"

    }

    override val hasOptionMenu = false

    private val mapper: Function<LoginInfo, LoginViewData>
        get() = Function { loginViewDataMapper.loginToLoginViewData(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LoginViews(inflater, container!!, toaster)
        controller = LoginInjector.createController(
            loginEffectHandlers.createEffectHandlers(view),
            resolveDefaultModel(savedInstanceState)
        )
        controller.connect(contramap(mapper, view))
        return view.rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(
            ARGUMENT_LOGIN_INFO,
            LoginInfoBundlePacker.loginInfoToBundle(controller.model)
        )
    }

    override fun resolveDefaultModel(savedInstanceState: Bundle?): LoginInfo {
        return if (savedInstanceState != null) {
            LoginInfoBundlePacker.loginInfoFromBundle(savedInstanceState)
        } else {
            LoginInfo.DEFAULT
        }
    }

}