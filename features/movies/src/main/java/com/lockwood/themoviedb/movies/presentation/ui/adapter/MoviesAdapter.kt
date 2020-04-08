package com.lockwood.themoviedb.movies.presentation.ui.adapter

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseAdapter
import com.lockwood.core.ui.BaseViewHolder
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.glide.extensions.drawableRequest
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.databinding.ItemMovieEmtpyBinding
import com.lockwood.themoviedb.movies.databinding.ItemMovieGridBinding
import com.lockwood.themoviedb.movies.databinding.ItemMovieListBinding
import com.lockwood.themoviedb.movies.domain.model.MovieItem
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType.ITEM_VIEW_TYPE_LIST
import com.lockwood.themoviedb.movies.utils.MovieUtils
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MoviesAdapter(
    data: List<MovieItem> = emptyList(),
    val itemViewType: Int = ITEM_VIEW_TYPE_LIST
) : BaseAdapter<MovieItem>(data) {

    interface MoviesAdapterListener {

        fun onMovieClick(item: MovieItem)

    }

    lateinit var listener: MoviesAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_CONTENT -> {
                return if (itemViewType == ITEM_VIEW_TYPE_LIST) {
                    val binding =
                        parent.inflateViewBinding<ItemMovieListBinding>(attachToRoot = false)
                    MoviesListViewHolder(binding)
                } else {
                    val binding =
                        parent.inflateViewBinding<ItemMovieGridBinding>(attachToRoot = false)
                    MoviesGridViewHolder(binding)
                }
            }
            else -> {
                val binding =
                    parent.inflateViewBinding<ItemMovieEmtpyBinding>(attachToRoot = false)
                EmptyMoviesViewHolder(binding)
            }
        }
    }

    abstract inner class MoviesViewHolder<T : ViewBinding>(private val itemBinding: T) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) {
            val movie = data[position]

            val context = itemBinding.root.context
            val resourceReader = ResourceReader(context)
            val placeholder = resourceReader.drawable(R.drawable.ic_poster_placeholder)
            val roundedCornersTransformation = RoundedCornersTransformation(
                resourceReader.dimenInPx(R.dimen.item_movies_corner_radius),
                0,
                RoundedCornersTransformation.CornerType.ALL
            )
            val imageRequest = context.drawableRequest(
                resourceReader = resourceReader,
                placeholder = placeholder,
                fallback = placeholder,
                error = placeholder
            ).apply(RequestOptions.bitmapTransform(roundedCornersTransformation))

            val ratingColorRes = MovieUtils.ratingToColorRes(movie.voteAverage)
            val ratingColor = resourceReader.color(ratingColorRes)

            bindMovie(itemBinding, movie, imageRequest, ratingColor)
        }

        abstract fun bindMovie(
            itemBinding: T,
            movie: MovieItem,
            avatarRequest: RequestBuilder<Drawable>,
            ratingColor: Int
        )
    }

    inner class MoviesListViewHolder(itemBinding: ItemMovieListBinding) :
        MoviesViewHolder<ItemMovieListBinding>(itemBinding) {

        override fun bindMovie(
            itemBinding: ItemMovieListBinding,
            movie: MovieItem,
            avatarRequest: RequestBuilder<Drawable>,
            ratingColor: Int
        ) = with(itemBinding) {
            val resourceReader = ResourceReader(itemBinding.root.context)

            itemMovieTitle.text = movie.title
            itemMovieOriginalTitle.text = MovieUtils.buildOriginalTitle(
                resourceReader,
                movie.originalTitle,
                movie.releaseDate
            )

            itemMovieVoteAverage.setTextColor(ratingColor)
            itemMovieVoteAverage.text = movie.voteAverage.toString()
            itemMoviePopularity.text = movie.popularity.toString()

            itemMovieImage.load(movie.poster, avatarRequest).waitForLayout()

            root.setOnClickListener { listener.onMovieClick(movie) }
        }
    }

    inner class MoviesGridViewHolder(itemBinding: ItemMovieGridBinding) :
        MoviesViewHolder<ItemMovieGridBinding>(itemBinding) {

        override fun bindMovie(
            itemBinding: ItemMovieGridBinding,
            movie: MovieItem,
            avatarRequest: RequestBuilder<Drawable>,
            ratingColor: Int
        ) = with(itemBinding) {
            val resourceReader = ResourceReader(itemBinding.root.context)

            itemMovieTitle.text = movie.title
            itemMovieOriginalTitle.text = MovieUtils.buildOriginalTitle(
                resourceReader,
                movie.originalTitle,
                movie.releaseDate
            )

            itemMovieVoteAverage.setTextColor(ratingColor)
            itemMovieVoteAverage.text = movie.voteAverage.toString()
            itemMoviePopularity.text = movie.popularity.toString()

            itemMovieImage.load(movie.poster, avatarRequest).waitForLayout()

            root.setOnClickListener { listener.onMovieClick(movie) }
        }
    }

    inner class EmptyMoviesViewHolder(itemBinding: ItemMovieEmtpyBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) = Unit
    }

}