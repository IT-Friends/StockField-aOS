package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.FundModel

interface FundRepository {

    suspend fun getFund(fund: String): FundModel

}