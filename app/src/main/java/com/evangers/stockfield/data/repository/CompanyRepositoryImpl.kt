package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.CompanyMapper
import com.evangers.stockfield.domain.model.CompanyFundsModel
import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.repository.CompanyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val stockFieldApi: StockFieldApi,
    private val companyMapper: CompanyMapper
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

    override suspend fun getFunds(companyIndex: Int): CompanyFundsModel {
        return when (companyIndex) {
            0 -> {
                CompanyFundsModel(listOf("ARKK", "ARKQ", "ARKW", "ARKF", "ARKG", "ARKX"))
            }
            else -> {
                val list = mutableListOf<String>()
                for (i in 1..10) {
                    list.add("$companyIndex${10 * i}")
                }
                CompanyFundsModel(list)
            }
        }


    }
}