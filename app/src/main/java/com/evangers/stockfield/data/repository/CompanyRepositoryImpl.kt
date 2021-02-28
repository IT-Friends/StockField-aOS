package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.CompanyMapper
import com.evangers.stockfield.data.mapper.FundMapper
import com.evangers.stockfield.domain.model.CompanyFundsModel
import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.repository.CompanyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val stockFieldApi: StockFieldApi,
    private val companyMapper: CompanyMapper,
    private val fundMapper: FundMapper
) : CompanyRepository {
    override suspend fun getCompanies(): List<CompanyModel> {
        try {
            val response = withContext(Dispatchers.IO) {
                stockFieldApi.getCompanies()
            }
            return response.map { companyEntity ->
                companyMapper.mapFromEntity(companyEntity)
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    override suspend fun getFundsFromCompany(companyIndex: Int): CompanyFundsModel {
        try {
            val response = withContext(Dispatchers.IO) {
                stockFieldApi.getFundsFromCompany(companyIndex)
            }
            return CompanyFundsModel(
                fundList = response.map {
                    fundMapper.mapFromEntity(it)
                }
            )
        } catch (exception: Exception) {
            throw exception
        }
    }
}