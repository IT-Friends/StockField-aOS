package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.repository.IUserRepository
import javax.inject.Inject

class HasUsedOverHoneymoon @Inject constructor(
    private val userRepositoryImpl: IUserRepository
) : SuspendUseCase<Long, HasUsedOverHoneymoon.Response> {

    sealed class Response {
        object Success : Response()
        object Failure : Response()
    }

    override suspend fun invoke(honeyMoonDay: Long): Response {
        return try {
            val lastSignInTime = userRepositoryImpl.getInitialOpenTime()
            val diff = System.currentTimeMillis() - lastSignInTime
            val curDay = diff / 1000 / 60 / 60 / 24
            when (curDay >= honeyMoonDay) {
                true -> {
                    Response.Success
                }
                false -> {
                    Response.Failure
                }
            }
        } catch (e: Exception) {
            Response.Failure
        }

    }
}