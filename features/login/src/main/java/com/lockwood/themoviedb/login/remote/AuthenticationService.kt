package com.lockwood.themoviedb.login.remote

import com.lockwood.themoviedb.login.remote.model.body.CreateSessionBodyModel
import com.lockwood.themoviedb.login.remote.model.body.ValidateWithLoginBodyModel
import com.lockwood.themoviedb.login.remote.model.response.CreateRequestTokenResponseModel
import com.lockwood.themoviedb.login.remote.model.response.CreateSessionResponseModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {

    @GET("authentication/token/new")
    fun createRequestToken(): Single<CreateRequestTokenResponseModel>

    @POST("authentication/token/validate_with_login")
    fun validateTokenWithLogin(
        @Body loginBody: ValidateWithLoginBodyModel
    ): Completable

    @POST("authentication/session/new")
    fun createSession(
        @Body sessionBody: CreateSessionBodyModel
    ): Single<CreateSessionResponseModel>

}
