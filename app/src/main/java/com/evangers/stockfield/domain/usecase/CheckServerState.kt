package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.repository.IServerStateRepository
import com.evangers.stockfield.domain.repository.IUserRepository
import com.evangers.stockfield.domain.throwables.NoTokenException
import com.evangers.stockfield.domain.throwables.ServerIsDownException
import com.evangers.stockfield.domain.throwables.UnknownException
import javax.inject.Inject

class CheckServerState @Inject constructor(
    private val userRepository: IUserRepository,
    private val serverRepository: IServerStateRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun invoke(request: Unit) {
        try {
            userRepository.getToken()
            val serverState = serverRepository.getServerState()
            if (serverState.isOnMaintenance) {
                throw ServerIsDownException(serverState.message)
            }
        } catch (e: NoTokenException) {
            throw e
        } catch (e: Exception) {
            throw UnknownException()
        }
    }
}