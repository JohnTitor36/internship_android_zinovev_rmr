package com.lockwood.themoviedb.login.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.remote.model.body.ValidateWithLoginBodyModel
import javax.inject.Inject

class ValidateWithLoginBodyEntityMapper @Inject constructor() :
    EntityRemoteMapper<ValidateWithLoginBodyModel, ValidateWithLoginBodyEntity> {

    override fun mapFromRemote(type: ValidateWithLoginBodyModel): ValidateWithLoginBodyEntity {
        return ValidateWithLoginBodyEntity(type.username, type.password, type.requestToken)
    }

    override fun mapToRemote(type: ValidateWithLoginBodyEntity): ValidateWithLoginBodyModel {
        return ValidateWithLoginBodyModel(type.username, type.password, type.requestToken)
    }

}