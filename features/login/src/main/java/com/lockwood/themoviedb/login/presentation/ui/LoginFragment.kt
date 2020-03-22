package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.lockwood.core.extensions.afterMeasured
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.extensions.dimenInPx
import com.lockwood.core.extensions.newIntent
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.snackbar.SnackbarMaker
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    companion object {

        private const val MAIN_ACTIVITY_CLASS_NAME = ".presentation.ui.MainActivity"
    }

    @Inject
    lateinit var viewModelFactory: Provider<LoginViewModel>

    @Inject
    lateinit var snackbarMaker: SnackbarMaker

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

    private fun addViewListeners() {
        login_edit_text.addTextChangedListener { viewModel.setLogin(it.toString()) }
        password_edit_text.addTextChangedListener { viewModel.setPassword(it.toString()) }

        password_edit_text.addTextChangedListener { viewModel.setPassword(it.toString()) }
        sign_in_button.setOnClickListener {
            hideKeyboard()
            viewModel.login()
        }

        checkKeyboardVisibility()
    }

    private fun checkKeyboardVisibility(){
        val rootActivityView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootActivityView.afterMeasured {
            val heightDifference = rootActivityView.rootView.height - rootActivityView.height
            val isKeyboardOpened = heightDifference > requireContext().dimenInPx(R.dimen.keyboard_probably_height)
            viewModel.keyboardOpened.value = isKeyboardOpened
        }
    }

    private fun observeLiveDataChanges() = with(viewModel) {
        val lifecycleOwner = viewLifecycleOwner

        val validLengthObserver = Observer<String> { checkIsValidCredentialsLength() }
        loginLiveData.observe(lifecycleOwner, validLengthObserver)
        passwordLiveData.observe(lifecycleOwner, validLengthObserver)
        isCredentialsLengthValid.observe(lifecycleOwner, Observer { isValidLength ->
            sign_in_button.isEnabled = isValidLength
        })

        isLoadingLiveData.observe(lifecycleOwner, Observer { isLoading ->
            val loginProgressBar = requireActivity().findViewById<View>(R.id.login_progress_bar)
            if (isLoading) {
                loginProgressBar.isVisible = true
                rootView.isVisible = false
            } else {
                loginProgressBar.isVisible = false
                rootView.isVisible = true
            }
        })

        errorMessageLiveData.observe(lifecycleOwner, Observer { message ->
            login_error_text_view.text = message
            login_error_text_view.isVisible = !message.isNullOrEmpty()
        })

        // TODO: Заменить на переход к пин коду
        openNextActivityEvent.observe(lifecycleOwner, Observer {
            val intent = newIntent(
                requireContext(),
                MAIN_ACTIVITY_CLASS_NAME
            )
            startActivity(intent)
        })

        noInternetConnectionEvent.observe(lifecycleOwner, Observer {
            snackbarMaker.snackbar(
                sign_in_button,
                getString(R.string.title_check_network_connection)
            )
        })

        keyboardOpened.observe(lifecycleOwner, Observer { keyboardOpened ->
            login_title_text_view.isVisible = !keyboardOpened
            login_hint_text_view.isVisible = !keyboardOpened
        })
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