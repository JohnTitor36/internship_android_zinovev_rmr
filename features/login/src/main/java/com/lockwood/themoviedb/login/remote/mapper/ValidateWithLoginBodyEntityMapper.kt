package com.lockwood.themoviedb.login.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.remote.model.body.ValidateWithLoginBodyModel
import javax.inject.Inject

class ValidateWithLoginBodyEntityMapper @Inject constructor() :
    EntityRemoteMapper<ValidateWithLoginBodyModel, ValidateWithLoginBodyEntity> {

    override fun mapFromRemote(type: ValidateWithLoginBodyModel): ValidateWithLoginBodyEntity {
        return ValidateWithLoginBodyEntity(
            username = type.username,
            password = type.password,
            requestToken = type.requestToken
        )
    }

    override fun mapToRemote(type: ValidateWithLoginBodyEntity): ValidateWithLoginBodyModel {
        return ValidateWithLoginBodyModel(
            username = type.username,
            password = type.password,
            requestToken = type.requestToken
        )
    }

}