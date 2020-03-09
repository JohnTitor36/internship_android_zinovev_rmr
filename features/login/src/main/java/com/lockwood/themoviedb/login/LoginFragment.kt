package com.lockwood.themoviedb.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lockwood.core.extensions.newFragment
import com.lockwood.core.ui.BaseMobiusFragment
import com.lockwood.themoviedb.login.data.LoginModel
import com.lockwood.themoviedb.login.domain.LoginEffect
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.view.LoginViews

class LoginFragment : BaseMobiusFragment<LoginModel, LoginEvent, LoginEffect>() {

    companion object {

        fun newInstance() = newFragment<LoginFragment>()
    }

    override val hasOptionMenu = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LoginViews(inflater, container!!)
        return view.rootView
    }

}