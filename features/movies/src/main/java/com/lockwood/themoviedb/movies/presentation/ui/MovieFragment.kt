package com.lockwood.themoviedb.movies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.movies.databinding.FragmentMovieBinding
import com.lockwood.themoviedb.movies.di.component.DaggerMovieComponent
import javax.inject.Inject

class MovieFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    private val binding: FragmentMovieBinding by viewBinding()

    val args: MovieFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflateViewBinding<FragmentMovieBinding>(container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieNameTitle.text = args.movieIdArg.toString()
    }

    private fun inject() {
        DaggerMovieComponent.builder()
            .build()
            .inject(this)
    }

}