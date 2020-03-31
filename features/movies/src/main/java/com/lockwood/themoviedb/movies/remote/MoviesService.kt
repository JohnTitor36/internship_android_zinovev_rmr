package com.lockwood.themoviedb.movies.remote

import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<SearchMoviesResponseModel>

}
