package com.evangers.stockfield.domain.repository

interface IUserRepository {
    suspend fun getToken(): String

    fun setInitialOpenTime(time: Long)
    fun getInitialOpenTime(): Long
}