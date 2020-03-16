package com.lockwood.themoviedb.login.remote.model.body

import androidx.annotation.Keep
import com.lockwood.core.network.common.JsonConstants
import com.squareup.moshi.Json

@Keep
data class CreateSessionBodyModel(
    @Json(name = JsonConstants.REQUEST_TOKEN)
    val requestToken: String // 1531f1a558c8357ce8990cf887ff196e8f5402ec
)