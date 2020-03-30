package com.lockwood.themoviedb.movies.presentation.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.movies.databinding.FragmentMoviesBinding
import com.lockwood.themoviedb.movies.databinding.FragmentSearchBinding
import com.lockwood.themoviedb.movies.di.component.list.DaggerMoviesComponent
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    private val binding: FragmentMoviesBinding by viewBinding()

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflateViewBinding<FragmentMoviesBinding>(container).root

    private fun inject() {
        DaggerMoviesComponent.builder()
            .build()
            .inject(this)
    }

}