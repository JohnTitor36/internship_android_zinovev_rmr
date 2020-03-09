package com.lockwood.themoviedb.login.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.lockwood.core.extensions.simpleTextWatcher
import com.lockwood.core.toaster.DefaultToaster
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.domain.LoginLoginTextChanged
import com.lockwood.themoviedb.login.domain.LoginPasswordTextChanged
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

    override fun connect(output: Consumer<LoginEvent>): Connection<LoginViewData> {
        val loginWatcher = simpleTextWatcher { output.accept(LoginLoginTextChanged(it)) }
        val passwordWatcher = simpleTextWatcher { output.accept(LoginPasswordTextChanged(it)) }
        loginEditText.addTextChangedListener(loginWatcher)
        passwordEditText.addTextChangedListener(passwordWatcher)
        signInButton.setOnClickListener { output.accept(LoginRequested) }

        return object : Connection<LoginViewData> {
            override fun accept(value: LoginViewData) {
                render(value)
            }

            override fun dispose() {
                signInButton.setOnClickListener(null)
                loginEditText.removeTextChangedListener(loginWatcher)
                passwordEditText.removeTextChangedListener(passwordWatcher)
            }
        }
    }

    // TODO: Заменить на переход в MainActivity
    override fun showLoginComplete() {
        errorView.visibility = View.GONE
        toaster.toast("Валидация пройдена")
    }

    override fun showLoginInvalidInfo() = with(errorView) {
        visibility = View.VISIBLE
        // TODO: hide keyboard
        // TODO: remove focus
        text = ctx.getString(R.string.title_invalid_login_or_password_error)
    }

    private fun render(viewData: LoginViewData) {
        signInButton.isEnabled = viewData.isValidInput
    }


}