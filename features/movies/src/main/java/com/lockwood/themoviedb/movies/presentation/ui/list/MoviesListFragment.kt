package com.lockwood.themoviedb.movies.presentation.ui.list

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.di.component.list.DaggerMoviesListComponent
import javax.inject.Inject

class MoviesListFragment : BaseFragment(R.layout.fragment_movies_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MoviesListViewModel by viewModels { viewModelFactory }

    override val hasOptionMenu: Boolean = true

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerMoviesListComponent.builder()
            .build()
            .inject(this)
    }

}