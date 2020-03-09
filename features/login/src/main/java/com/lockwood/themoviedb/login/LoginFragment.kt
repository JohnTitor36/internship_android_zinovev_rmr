package com.lockwood.themoviedb.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lockwood.core.extensions.newFragment
import com.lockwood.core.ui.BaseMobiusFragment
import com.lockwood.themoviedb.login.data.LoginInfo
import com.lockwood.themoviedb.login.domain.LoginEffect
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.effecthandlers.LoginEffectHandlers
import com.lockwood.themoviedb.login.view.LoginViewData
import com.lockwood.themoviedb.login.view.LoginViewDataMapper
import com.lockwood.themoviedb.login.view.LoginViews
import com.spotify.mobius.extras.Connectables.contramap
import com.spotify.mobius.functions.Function

class LoginFragment : BaseMobiusFragment<LoginInfo, LoginEvent, LoginEffect>() {

    companion object {

        fun newInstance() = newFragment<LoginFragment>()
    }

    override val hasOptionMenu = false

    private val mapper: Function<LoginInfo, LoginViewData>
        get() = Function { LoginViewDataMapper.loginToLoginViewData(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LoginViews(inflater, container!!)
        controller = LoginInjector.createController(
            LoginEffectHandlers.createEffectHandlers(view),
            resolveDefaultModel(savedInstanceState)
        )
        controller.connect(contramap(mapper, view))
        return view.rootView
    }

    // TODO: add onSaveInstanceState for login and password
    override fun resolveDefaultModel(savedInstanceState: Bundle?): LoginInfo {
        return LoginInfo("", "")
    }

}