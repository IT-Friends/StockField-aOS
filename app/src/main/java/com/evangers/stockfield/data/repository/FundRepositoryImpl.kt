package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.FundMapper
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FundRepositoryImpl @Inject constructor(
    private val stockFieldApi: StockFieldApi,
    private val fundMapper: FundMapper
) : FundRepository {

    override suspend fun getFund(fund: String): FundModel {
        try {
            val response = withContext(Dispatchers.IO) {
                stockFieldApi.getFundInfo(fund)
            }
            return fundMapper.mapFromEntity(response)
        } catch (exception: Exception) {
            throw exception
        }
    }
}