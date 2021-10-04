package com.evangers.stockfield.data.repository

import com.evangers.stockfield.domain.repository.IUserRepository
import com.evangers.stockfield.domain.throwables.NoTokenException
import com.evangers.stockfield.ui.util.debugLog
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
) : IUserRepository {
    override suspend fun getToken(): String {
        return suspendCoroutine { ct ->
            val firebaseAppCheck = FirebaseAppCheck.getInstance()
            firebaseAppCheck.addAppCheckListener { token ->
                debugLog("token : ${token.token}")
                if (token.token.isEmpty()) {
                    ct.resumeWithException(NoTokenException("token is empty"))
                } else {
                    ct.resume(token.token)
                }
            }
            firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance())
        }
    }
}