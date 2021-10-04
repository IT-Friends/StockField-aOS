package com.evangers.stockfield.data.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ServerStateEntity(
    val onMaintenance: Boolean? = null,
    val message: String? = ""
)