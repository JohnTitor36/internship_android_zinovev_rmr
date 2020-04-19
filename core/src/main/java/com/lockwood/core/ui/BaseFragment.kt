package com.lockwood.core.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lockwood.core.event.*
import com.lockwood.core.extensions.buildNavOptions
import com.lockwood.core.extensions.buildSnackbar
import com.lockwood.core.extensions.launchActivity
import com.lockwood.core.extensions.navOptionsFromAction
import com.lockwood.core.message.MessageView
import timber.log.Timber

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

    protected open fun setupViews() = Unit

    protected open fun handlePermission(requestCode: Int, permissionGranted: Boolean) = Unit

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

    fun hasPermission(permission: String): Boolean {
        val checkSelfPermission = ContextCompat.checkSelfPermission(requireContext(), permission)
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(
        permission: String,
        requestCode: Int
    ) {
        requestPermissions(arrayOf(permission), requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val grantResultsNotEmpty = grantResults.isNotEmpty()
        val permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        Timber.d("grantResultsNotEmpty: $grantResultsNotEmpty")
        Timber.d("permissionGranted: $permissionGranted")
        handlePermission(
            requestCode = requestCode,
            permissionGranted = grantResultsNotEmpty && permissionGranted
        )
    }

    @CallSuper
    protected open fun onOnEvent(event: Event) {
        when (event) {
            is MessageEvent -> {
                showMessage(event.message)
            }
            is ErrorMessageEvent -> {
                showError(event.errorMessage)
            }
            is NavigationEvent -> {
                navigateTo(event.direction, event.navOptions)
            }
            is BackPressEvent -> {
                requireActivity().onBackPressed()
            }
            is LaunchActivityEvent -> {
                requireContext().launchActivity(event.className) {
                    flags = event.flags
                }
            }
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