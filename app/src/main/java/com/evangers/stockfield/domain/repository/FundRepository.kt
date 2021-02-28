package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.FundHoldingsModel

interface FundRepository {

    suspend fun getFund(fund: String): FundHoldingsModel

}