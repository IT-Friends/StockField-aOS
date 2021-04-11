package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.model.HistoryModel
import com.evangers.stockfield.domain.model.ListResponseModel

interface FundRepository {
    suspend fun getFundsFromCompany(
        companyIndex: Int
    ): ListResponseModel<FundModel>

    suspend fun getFundComparison(
        page: Int = 0,
        itemsPerPage: Int? = null,
        fund: String,
        dateFrom: String? = null,
        dateTo: String? = null,
        order: String = "ranking",
    ): ListResponseModel<FundHoldingComparisonModel>

    suspend fun getFundHistory(
        page: Int = 0,
        itemsPerPage: Int? = null,
        fundName: String,
        ticker: String
    ): ListResponseModel<HistoryModel>

}