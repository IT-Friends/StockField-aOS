package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.entity.ServerStateEntity
import com.evangers.stockfield.domain.model.ServerState
import com.evangers.stockfield.domain.repository.IServerStateRepository
import com.evangers.stockfield.ui.util.debugLog
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ServerStateRepositoryImpl @Inject constructor(
) : IServerStateRepository {
    private val database =
        Firebase.database("https://stockfield-e146c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val databaseReference = database.getReference("serverState")

    override suspend fun getServerState(): ServerState {
        return suspendCoroutine { ct ->
            databaseReference.get().addOnSuccessListener { snapshot ->
                val state = snapshot.getValue(ServerStateEntity::class.java)
                val isOnMaintenance = state?.isOnMaintenance ?: false
                val message = state?.message ?: ""
                debugLog("state?.isOnMaintenance : ${state?.isOnMaintenance}, state?.message : ${state?.message}")
                ct.resume(ServerState(isOnMaintenance, message))
            }.addOnFailureListener {
                debugLog("${it.message}")
                ct.resumeWithException(it)
            }.addOnCompleteListener {
                debugLog("complete")
            }
        }
    }
}
