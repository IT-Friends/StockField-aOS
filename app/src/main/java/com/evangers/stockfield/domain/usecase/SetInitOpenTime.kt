package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.repository.IUserRepository
import javax.inject.Inject

class SetInitOpenTime @Inject constructor(
    private val userRepository: IUserRepository
) : SuspendUseCase<Long, Unit> {
    override suspend fun invoke(initTimeInMille: Long) {
        if (userRepository.getInitialOpenTime() == Long.MAX_VALUE) {
            userRepository.setInitialOpenTime(initTimeInMille)
        }
    }

}