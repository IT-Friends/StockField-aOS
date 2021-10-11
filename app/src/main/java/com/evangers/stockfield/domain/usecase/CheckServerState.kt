package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.repository.IServerStateRepository
import com.evangers.stockfield.domain.repository.IUserRepository
import com.evangers.stockfield.domain.throwables.ServerIsDownException
import javax.inject.Inject

class CheckServerState @Inject constructor(
    private val userRepository: IUserRepository,
    private val serverRepository: IServerStateRepository
) : SuspendUseCase<Unit, Unit> {

    override suspend fun invoke(request: Unit) {
        userRepository.getToken()
        val serverState = serverRepository.getServerState()
        if (serverState.onMaintenance) {
            throw ServerIsDownException(serverState.message)
        }
    }
}