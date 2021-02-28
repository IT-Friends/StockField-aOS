package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.FundHoldingsMapper
import com.evangers.stockfield.domain.model.FundHoldingsModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FundRepositoryImpl @Inject constructor(
    private val stockFieldApi: StockFieldApi,
    private val fundHoldingsMapper: FundHoldingsMapper
) : FundRepository {

    override suspend fun getFund(fund: String): FundHoldingsModel {
        try {
            val response = withContext(Dispatchers.IO) {
                stockFieldApi.getFundInfo(fund)
            }
            return fundHoldingsMapper.mapFromEntity(response)
        } catch (exception: Exception) {
            throw exception
        }
    }
}