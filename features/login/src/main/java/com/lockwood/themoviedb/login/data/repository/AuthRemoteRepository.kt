package com.lockwood.themoviedb.login.data.repository

import android.content.Context
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.data.mapper.CredentialsMapper
import com.lockwood.themoviedb.login.data.source.AuthRemoteDataSource
import com.lockwood.themoviedb.login.presentation.model.Credentials
import io.reactivex.Completable
import javax.inject.Inject

@FeatureScope
class AuthRemoteRepository @Inject constructor(
    private val credentialsMapper: CredentialsMapper,
    private val context: Context
) : AuthRemoteDataSource {

    // TODO: Убрать заглушки
    override fun authUser(credentials: Credentials): Completable {
        val credentialsEntity = credentialsMapper.mapToEntity(credentials)
        val isRightLogin = credentialsEntity.login == DUMMY_LOGIN
        val isRightPassword = credentialsEntity.password == DUMMY_PASSWORD
        return if (isRightLogin && isRightPassword) {
            Completable.complete()
        } else {
            Completable.error { Throwable(context.getString(R.string.title_invalid_login_or_password_error)) }
        }
    }

    companion object {

        private const val DUMMY_LOGIN = "admin"
        private const val DUMMY_PASSWORD = "rmr2020"
    }

}