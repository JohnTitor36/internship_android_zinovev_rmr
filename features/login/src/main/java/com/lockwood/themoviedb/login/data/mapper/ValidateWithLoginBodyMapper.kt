package com.lockwood.themoviedb.login.data.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import javax.inject.Inject

class ValidateWithLoginBodyMapper @Inject constructor() :
    EntityRemoteMapper<ValidateWithLoginBodyEntity, ValidateWithLoginBody> {

    override fun mapFromRemote(type: ValidateWithLoginBodyEntity): ValidateWithLoginBody {
        return ValidateWithLoginBody(
            username = type.username,
            password = type.password,
            requestToken = type.requestToken
        )
    }

    override fun mapToRemote(type: ValidateWithLoginBody): ValidateWithLoginBodyEntity {
        return ValidateWithLoginBodyEntity(
            username = type.username,
            password = type.password,
            requestToken = type.requestToken
        )
    }

}