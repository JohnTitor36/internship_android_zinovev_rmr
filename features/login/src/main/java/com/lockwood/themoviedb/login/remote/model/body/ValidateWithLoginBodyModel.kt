package com.lockwood.themoviedb.login.remote.model.body

import androidx.annotation.Keep
import com.lockwood.core.network.common.JsonConstants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ValidateWithLoginBodyModel(
    @Json(name = "username")
    val username: String, // johnny_appleseed
    @Json(name = "password")
    val password: String, // test123
    @Json(name = JsonConstants.REQUEST_TOKEN)
    val requestToken: String // 1531f1a558c8357ce8990cf887ff196e8f5402ec
)