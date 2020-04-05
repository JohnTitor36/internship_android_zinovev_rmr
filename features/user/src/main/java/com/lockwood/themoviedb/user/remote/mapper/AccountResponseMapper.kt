package com.lockwood.themoviedb.user.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.user.data.model.AccountResponseEntity
import com.lockwood.themoviedb.user.remote.model.response.AccountResponseModel
import javax.inject.Inject

typealias Avatar = AccountResponseModel.Avatar
typealias Gravatar = AccountResponseModel.Avatar.Gravatar

class AccountResponseMapper @Inject constructor() :
    EntityRemoteMapper<AccountResponseModel, AccountResponseEntity> {

    override fun mapFromRemote(type: AccountResponseModel): AccountResponseEntity {
        with(type) {
            return AccountResponseEntity(
                avatar.gravatar.url,
                id,
                iso6391,
                iso31661,
                name,
                includeAdult,
                username
            )
        }

    }

    override fun mapToRemote(type: AccountResponseEntity): AccountResponseModel {
        with(type) {
            return AccountResponseModel(
                Avatar(Gravatar(gravatarUrl)),
                id,
                iso6391,
                iso31661,
                name,
                includeAdult,
                username
            )
        }
    }

}