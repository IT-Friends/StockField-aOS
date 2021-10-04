package com.evangers.stockfield.domain.model

data class ServerState(
    val onMaintenance: Boolean = false,
    val message: String = ""
)
