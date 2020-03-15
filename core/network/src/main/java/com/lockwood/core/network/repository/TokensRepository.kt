package com.lockwood.core.network.repository

interface TokensRepository {

    fun getAccessToken(): String

}