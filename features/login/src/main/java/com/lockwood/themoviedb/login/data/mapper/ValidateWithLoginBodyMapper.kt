package com.lockwood.themoviedb.login.data.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import javax.inject.Inject

class ValidateWithLoginBodyMapper @Inject constructor() :
    EntityRemoteMapper<ValidateWithLoginBodyEntity, ValidateWithLoginBody> {

    override fun mapFromRemote(type: ValidateWithLoginBodyEntity): ValidateWithLoginBody {
        return ValidateWithLoginBody(type.username, type.password, type.requestToken)
    }

    override fun mapToRemote(type: ValidateWithLoginBody): ValidateWithLoginBodyEntity {
        return ValidateWithLoginBodyEntity(type.username, type.password, type.requestToken)
    }

}