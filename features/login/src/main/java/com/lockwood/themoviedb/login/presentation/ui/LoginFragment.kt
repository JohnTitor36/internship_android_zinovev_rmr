package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.lockwood.core.extensions.*
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    companion object {

        private const val MAIN_ACTIVITY_CLASS_NAME =
            "com.lockwood.themoviedb.presentation.ui.MainActivity"
    }

    @Inject
    lateinit var viewModelFactory: Provider<LoginViewModel>

    private lateinit var viewModel: LoginViewModel

    override val hasOptionMenu: Boolean = false

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveDataChanges()
        addViewListeners()
    }

    override fun showMessage(message: String) {
        rootView.buildSnackbar(message).show()
    }

    override fun showError(error: String) = with(login_error_text_view){
        text = error
        isVisible = error.isNotEmpty()
    }

    private fun addViewListeners() {
        login_edit_text.addTextChangedListener { viewModel.setLogin(it.toString()) }
        password_edit_text.addTextChangedListener { viewModel.setPassword(it.toString()) }

        sign_in_button.setOnClickListener {
            hideKeyboard()
            viewModel.login()
        }

        checkKeyboardVisibility()
    }

    private fun checkKeyboardVisibility() {
        val rootActivityView =
            requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootActivityView.afterMeasured {
            val heightDifference = rootActivityView.rootView.height - rootActivityView.height
            val isKeyboardOpened =
                heightDifference > requireContext().dimenInPx(R.dimen.keyboard_probably_height)
            viewModel.keyboardOpened.value = isKeyboardOpened
        }
    }

    private fun observeLiveDataChanges() {
        observeCredentialsInputChanges()
        observeCredentialsLengthChanges()
        observeRequestChanges()
        observeConnectivityChanges()
        observeNavigationChanges()
        observeKeyboardAppearanceChanges()
    }

    private fun observeCredentialsInputChanges() {
        val validLengthObserver = Observer<String> { viewModel.checkIsValidCredentialsLength() }
        observe(viewModel.loginLiveData, validLengthObserver)
        observe(viewModel.passwordLiveData, validLengthObserver)
    }

    private fun observeCredentialsLengthChanges() {
        observe(viewModel.isCredentialsLengthValid) { sign_in_button.isEnabled = it }
    }

    private fun observeRequestChanges() {
        observe(viewModel.isLoadingLiveData) { isLoading ->
            val loginProgressBar = requireActivity().findViewById<View>(R.id.login_progress_bar)
            loginProgressBar.isVisible = isLoading
        }
        observe(viewModel.errorMessageLiveData) { message ->
            if (message != null) {
                showError(message)
            }
        }
    }

    private fun observeConnectivityChanges() {
        observe(viewModel.noInternetConnectionEvent) {
            val checkNetworkMessage = getString(R.string.title_check_network_connection)
            showMessage(checkNetworkMessage)
        }
    }

    private fun observeNavigationChanges() {
        observe(viewModel.openNextActivityEvent) {
            // TODO: Заменить на переход к пин коду
            requireContext().launchActivity(MAIN_ACTIVITY_CLASS_NAME) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }

    private fun observeKeyboardAppearanceChanges() {
        observe(viewModel.keyboardOpened) { keyboardOpened ->
            login_title_text_view.isVisible = !keyboardOpened
            login_hint_text_view.isVisible = !keyboardOpened
        }
    }

    private fun inject() {
        DaggerLoginComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }

}