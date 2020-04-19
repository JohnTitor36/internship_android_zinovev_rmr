package com.lockwood.core.cryptographic

import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Cryptographer @Inject constructor(
    keySetHandle: KeysetHandle,
    private val base64Encoder: Base64Encoder
) {

    companion object {

        private val DEFAULT_CHARSET = Charsets.UTF_8

        private val EMPTY_ASSOCIATED_DATA = ByteArray(0)
    }

    private val aead = keySetHandle.getPrimitive(Aead::class.java)

    fun decryptString(string: String): String {
        val cipherText = base64Encoder.decode(string)
        val plainText = aead.decrypt(cipherText, EMPTY_ASSOCIATED_DATA)
        return plainText.toString(charset = DEFAULT_CHARSET)
    }

    fun encryptString(string: String): String {
        val plainText = string.toByteArray(charset = DEFAULT_CHARSET)
        val cipherText = aead.encrypt(plainText, EMPTY_ASSOCIATED_DATA)
        return base64Encoder.encode(cipherText)
    }


}