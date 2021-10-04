package com.evangers.stockfield.domain.repository

interface IUserRepository {
    suspend fun getToken(): String
}