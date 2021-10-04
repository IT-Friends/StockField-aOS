package com.evangers.stockfield.data.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ServerStateEntity(
    val isOnMaintenance: Boolean? = false,
    val message: String? = ""
)
