package com.lockwood.themoviedb.movies.presentation.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.movies.databinding.FragmentFavoriteMoviesBinding
import com.lockwood.themoviedb.movies.di.component.favorite.DaggerFavoriteMoviesComponent
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavoriteMoviesViewModel by viewModels { viewModelFactory }

    private val binding: FragmentFavoriteMoviesBinding by viewBinding()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = createView<FragmentFavoriteMoviesBinding>(inflater, container)

    private fun inject() {
        DaggerFavoriteMoviesComponent.builder()
            .build()
            .inject(this)
    }

}