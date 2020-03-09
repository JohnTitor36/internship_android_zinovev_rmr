package com.lockwood.themoviedb.login.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.lockwood.core.toaster.DefaultToaster
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.domain.LoginRequested
import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class LoginViews(
    inflater: LayoutInflater,
    container: ViewGroup
) : LoginViewActions, Connectable<LoginViewData, LoginEvent> {

    var rootView: View = inflater.inflate(R.layout.fragment_login, container, false)

    private val loginEditText: EditText = rootView.findViewById(R.id.loginEditText)
    private val passwordEditText: EditText = rootView.findViewById(R.id.passwordEditText)
    private val errorView: TextView = rootView.findViewById(R.id.errorView)
    private val signInButton: Button = rootView.findViewById(R.id.signInButton)

    private val ctx: Context
        get() = rootView.context

    private val toaster = DefaultToaster(ctx)

    override fun showLoginInvalidInfo() {

    }

    // TODO: Заменить на переход в MainActivity
    override fun showLoginComplete() {
        toaster.toast("Валидация пройдена")
    }

    override fun connect(output: Consumer<LoginEvent>): Connection<LoginViewData> {
        signInButton.setOnClickListener { output.accept(LoginRequested) }

        return object : Connection<LoginViewData> {
            override fun accept(value: LoginViewData) {
                render(value)
            }

            override fun dispose() {
                signInButton.setOnClickListener(null)
            }
        }
    }

    private fun render(viewData: LoginViewData) {
        signInButton.isEnabled = viewData.isValidInput
    }


}