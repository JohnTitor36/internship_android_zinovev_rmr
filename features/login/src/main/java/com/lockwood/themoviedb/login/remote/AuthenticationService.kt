package com.lockwood.themoviedb.login.remote

import com.lockwood.core.network.common.JsonConstants
import com.lockwood.themoviedb.login.remote.model.body.CreateSessionBodyModel
import com.lockwood.themoviedb.login.remote.model.body.ValidateWithLoginBodyModel
import com.lockwood.themoviedb.login.remote.model.response.CreateRequestTokenResponseModel
import com.lockwood.themoviedb.login.remote.model.response.CreateSessionResponseModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthenticationService {

    @GET("/authentication/token/new")
    fun createRequestToken(
        @Query(JsonConstants.API_KEY) apiKey: String
    ): Single<CreateRequestTokenResponseModel>

    @POST("/authentication/token/validate_with_login")
    fun validateTokenWithLogin(
        @Query(JsonConstants.API_KEY) apiKey: String,
        @Body loginBody: ValidateWithLoginBodyModel
    ): Completable

    @POST("/authentication/session/new")
    fun createSession(
        @Query(JsonConstants.API_KEY) apiKey: String,
        @Body sessionBody: CreateSessionBodyModel
    ): Single<CreateSessionResponseModel>

}
