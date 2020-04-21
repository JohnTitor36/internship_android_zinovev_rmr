package com.lockwood.themoviedb.movies.remote.service

import com.lockwood.themoviedb.movies.remote.model.body.MarkAsFavoriteBodyModel
import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface FavoriteMoviesService {

    @POST("account/{accountId}/favorite")
    fun markAsFavorite(
        @Path("accountId") accountId: String,
        @Body markAsFavoriteBody: MarkAsFavoriteBodyModel
    ): Completable

    @GET("account/{accountId}/favorite/movies")
    fun loadFavoriteMovies(
        @Path("accountId") accountId: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Observable<SearchMoviesResponseModel>

}
