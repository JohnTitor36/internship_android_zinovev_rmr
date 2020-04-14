package com.lockwood.themoviedb.movies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.databinding.FragmentMovieBinding
import com.lockwood.themoviedb.movies.di.component.DaggerMovieComponent
import com.lockwood.themoviedb.movies.domain.model.Movie
import com.lockwood.themoviedb.movies.extensions.loadMoviePosterRequest
import com.lockwood.themoviedb.movies.utils.MovieUtils.buildDurationTitle
import com.lockwood.themoviedb.movies.utils.MovieUtils.buildOriginalTitle
import com.lockwood.themoviedb.movies.utils.MovieUtils.ratingToColor
import javax.inject.Inject

class MovieFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var resourceReader: ResourceReader

    private val binding: FragmentMovieBinding by viewBinding()

    private val args: MovieFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView<FragmentMovieBinding>(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)

        val movieId = args.movieIdArg
        viewModel.onLoadMovie(movieId)
    }

    override fun setupViews() {
        with(binding.movieAppbar) {
            movieBackButton.setOnClickListener { requireActivity().onBackPressed() }
            movieFavoriteButton.setOnClickListener { viewModel.onFavoriteClicked() }
        }
    }

    private fun renderState(state: MovieViewState) {
        with(binding) {

            movieProgressBar.isVisible = state.loading
            movieContent.isVisible = !state.loading && state.movie != null

            val favoriteDrawable = if (state.favoriteMovie) {
                resourceReader.drawable(R.drawable.ic_favorite)
            } else {
                resourceReader.drawable(R.drawable.ic_favorite_border)
            }
            movieAppbar.movieFavoriteButton.setImageDrawable(favoriteDrawable)
            movieAppbar.movieFavoriteButton.isEnabled = !state.loading

            val movie = state.movie
            if (movie != null) {
                renderMovie(movie)
            }
        }
    }

    private fun renderMovie(movie: Movie) {
        val imageRequest = requireContext().loadMoviePosterRequest()

        val ratingColor = ratingToColor(
            resourceReader = resourceReader,
            rating = movie.voteAverage
        )

        with(binding.movieInfo) {

            movieTitle.text = movie.title
            movieOriginalTitle.text = buildOriginalTitle(
                resourceReader = resourceReader,
                title = movie.originalTitle,
                releaseDate = movie.releaseDate
            )

            movieVoteAverage.text = movie.voteAverage.toString()
            movieVoteAverage.setTextColor(ratingColor)

            val genres = movie.genreModels.map { it.name }
            movieGenres.text = genres.joinToString()

            movieDuration.text = buildDurationTitle(
                resourceReader = resourceReader,
                runtime = movie.runtime
            )

            moviePopularity.text = movie.popularity.toString()

            movieImage.load(
                url = movie.poster,
                request = imageRequest
            )
        }

        binding.movieDescription.text = movie.overview
    }

    private fun inject() {
        DaggerMovieComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }

}