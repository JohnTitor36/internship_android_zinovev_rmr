package com.lockwood.themoviedb.user.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.request.RequestOptions
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.glide.extensions.drawableRequest
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.user.R
import com.lockwood.themoviedb.user.databinding.FragmentUserBinding
import com.lockwood.themoviedb.user.di.component.DaggerUserComponent
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation
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
        val context = requireContext()
        val resourceReader = ResourceReader(context)
        val defaultAvatar = resourceReader.drawable(R.drawable.ic_avatar_default)
        val circleWithBorderTransformation = CropCircleWithBorderTransformation(
            resourceReader.dimenInPx(R.dimen.user_avatar_corners_size),
            resourceReader.color(R.color.avatar_corner)
        )
        val avatarRequest = context.drawableRequest(
            resourceReader = resourceReader,
            placeholder = defaultAvatar,
            fallback = defaultAvatar,
            error = defaultAvatar
        ).apply(RequestOptions.bitmapTransform(circleWithBorderTransformation))

        binding.userAvatarImageView.load(image, avatarRequest)
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