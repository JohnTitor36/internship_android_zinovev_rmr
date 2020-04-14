package com.lockwood.themoviedb.user.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.user.databinding.FragmentUserBinding
import com.lockwood.themoviedb.user.di.component.DaggerUserComponent
import com.lockwood.themoviedb.user.extensions.loadAvatarRequest
import javax.inject.Inject

class UserFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: UserViewModel by viewModels { viewModelFactory }

    private val binding: FragmentUserBinding by viewBinding()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView<FragmentUserBinding>(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewListeners()

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)
        viewModel.fetchAccountDetails()
    }

    private fun addViewListeners() {
        binding.signOutButton.setOnClickListener { viewModel.onLogoutClick() }
    }

    private fun renderState(state: UserViewState) {
        renderUserName(state.username)
        renderUserImage(state.image)
    }

    private fun renderUserName(username: String) {
        binding.userNameTextView.text = username
    }

    private fun renderUserImage(image: String) {
        val avatarRequest = requireContext().loadAvatarRequest()
        binding.userAvatarImageView.load(
            url = image,
            request = avatarRequest
        )
    }

    private fun inject() {
        DaggerUserComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }

}