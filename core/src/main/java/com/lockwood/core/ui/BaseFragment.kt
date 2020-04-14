package com.lockwood.core.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lockwood.core.event.ErrorMessageEvent
import com.lockwood.core.event.Event
import com.lockwood.core.event.MessageEvent
import com.lockwood.core.event.NavigationEvent
import com.lockwood.core.extensions.buildNavOptions
import com.lockwood.core.extensions.buildSnackbar
import com.lockwood.core.extensions.navOptionsFromAction
import com.lockwood.core.message.MessageView

abstract class BaseFragment : Fragment(), MessageView {

    protected lateinit var rootView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view

        setupViews()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    override fun showMessage(message: String) {
        rootView.buildSnackbar(message).show()
    }

    override fun showError(error: String) = Unit

    protected fun hideKeyboard() {
        with(requireContext().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager) {
            hideSoftInputFromWindow(rootView.windowToken, 0)
        }
        rootView.clearFocus()
    }

    protected open fun setupViews() = Unit

    @CallSuper
    protected open fun onOnEvent(event: Event) {
        when (event) {
            is MessageEvent -> showMessage(event.message)
            is ErrorMessageEvent -> showError(event.errorMessage)
            is NavigationEvent -> navigateTo(event.direction, event.navOptions)
        }
    }

    protected fun navigateTo(
        direction: NavDirections,
        navOptions: NavOptions? = null
    ) {
        val navController = findNavController()

        val navOptionsFromAction = navController.navOptionsFromAction(direction)
        val currentNavOptions = navOptions ?: navOptionsFromAction ?: NavOptions.Builder().build()
        val resultNavOptions = currentNavOptions.buildNavOptions()

        navController.navigate(direction, resultNavOptions)
    }

}