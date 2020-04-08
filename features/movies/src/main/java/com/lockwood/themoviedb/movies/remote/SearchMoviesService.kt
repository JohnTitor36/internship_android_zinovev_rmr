package com.lockwood.themoviedb.movies.remote

import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMoviesService {

    @GET("search/movie")
    fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Observable<SearchMoviesResponseModel>

    @GET("genre/movie/list")
    fun searchGenres(
        @Query("language") language: String
    ): Observable<SearchMoviesResponseModel>

}
