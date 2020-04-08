package com.lockwood.themoviedb.movies.remote

import com.lockwood.themoviedb.movies.remote.model.response.MovieResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{movieId}")
    fun loadMovie(
        @Path("movieId") movieId: Int,
        @Query("language") language: String
    ): Single<MovieResponseModel>

}
