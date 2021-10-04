package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.ServerState

interface IServerStateRepository {
    suspend fun getServerState(): ServerState
}