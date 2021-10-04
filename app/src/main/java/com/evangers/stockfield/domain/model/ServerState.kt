package com.evangers.stockfield.domain.model

data class ServerState(
    val isOnMaintenance: Boolean = false,
    val message: String = ""
)
