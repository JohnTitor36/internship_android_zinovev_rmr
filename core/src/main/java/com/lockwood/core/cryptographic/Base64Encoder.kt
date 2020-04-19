package com.lockwood.core.cryptographic

import android.util.Base64
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Base64Encoder @Inject constructor() {

    fun encode(
        input: ByteArray,
        flags: Int = Base64.DEFAULT
    ): String {
        return Base64.encodeToString(input, flags)
    }

    fun decode(
        input: String,
        flags: Int = Base64.DEFAULT
    ): ByteArray {
        return Base64.decode(input, flags)
    }

}