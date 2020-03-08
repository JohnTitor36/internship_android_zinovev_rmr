package com.lockwood.themoviedb.login.view

import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lockwood.themoviedb.login.domain.LoginEvent
import com.lockwood.themoviedb.login.domain.LoginRequested
import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.functions.Consumer

class LoginViews(
    private val container: ViewGroup,
    private val loginEditText: EditText,
    private val passwordEditText: EditText,
    private val signInButton: Button
) : LoginViewActions, Connectable<LoginViewData, LoginEvent> {

    private val context: Context
        get() = container.context

    private fun render(viewData: LoginViewData) {
        signInButton.isEnabled = viewData.isValidInput
    }

    override fun showLoginInvalidInfo() {
        Toast.makeText(context, "LoginInvalidInfo", Toast.LENGTH_SHORT).show()
    }

    override fun showLoginComplete() {
        Toast.makeText(context, "LoginComplete", Toast.LENGTH_SHORT).show()
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

}