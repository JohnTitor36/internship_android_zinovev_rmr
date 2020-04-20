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
import java.util.*
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
        // state-delegator пока не завёз, поэтому такой быдло код
        renderLoading(state.loading, state.movie)
        renderFavoriteButton(state.loading, state.favoriteMovie)
        renderMovie(state.movie)
    }

    private fun renderLoading(loading: Boolean, movie: Movie?) {
        with(binding) {
            movieProgressBar.isVisible = loading
            movieContent.isVisible = !loading && movie != null
        }
    }

    private fun renderFavoriteButton(loading: Boolean, favoriteMovie: Boolean) {
        val favoriteDrawable = if (favoriteMovie) {
            resourceReader.drawable(R.drawable.ic_favorite)
        } else {
            resourceReader.drawable(R.drawable.ic_favorite_border)
        }

        with(binding.movieAppbar.movieFavoriteButton) {
            isEnabled = !loading
            setImageDrawable(favoriteDrawable)
        }
    }

    private fun renderMovie(movie: Movie?) {
        if (movie == null) {
            return
        }

        renderMovieImage(movie.poster)
        renderMovieRating(movie.voteAverage)
        renderMovieTitles(movie.title, movie.originalTitle, movie.releaseDate)
        renderMovieDescription(movie.overview)
        renderMovieInfo(movie)
    }

    private fun renderMovieImage(poster: String) {
        val imageRequest = requireContext().loadMoviePosterRequest()

        binding.movieInfo.movieImage.load(
            url = poster,
            request = imageRequest
        )
    }

    private fun renderMovieRating(voteAverage: Double) {
        val ratingColor = ratingToColor(
            resourceReader = resourceReader,
            rating = voteAverage
        )

        with(binding.movieInfo.movieVoteAverage) {
            text = voteAverage.toString()
            setTextColor(ratingColor)
        }
    }

    private fun renderMovieTitles(title: String, originalTitle: String, releaseDate: Date) {

        with(binding.movieInfo) {
            movieTitle.text = title

            movieOriginalTitle.text = buildOriginalTitle(
                resourceReader = resourceReader,
                title = originalTitle,
                releaseDate = releaseDate
            )
        }
    }

    private fun renderMovieDescription(overview: String) {

        binding.movieDescription.text = overview
    }

    private fun renderMovieInfo(movie: Movie) {
        val genres = movie.genreModels.map { it.name }

        with(binding.movieInfo) {
            movieGenres.text = genres.joinToString()

            movieDuration.text = buildDurationTitle(
                resourceReader = resourceReader,
                runtime = movie.runtime
            )

            moviePopularity.text = movie.popularity.toString()
        }
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