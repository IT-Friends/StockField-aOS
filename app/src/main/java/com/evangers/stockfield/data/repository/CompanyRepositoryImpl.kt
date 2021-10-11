package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.CompanyMapper
import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.ListResponseModel
import com.evangers.stockfield.domain.repository.CompanyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val stockFieldApi: StockFieldApi,
    private val companyMapper: CompanyMapper
) : CompanyRepository {

    override suspend fun getCompanies(): ListResponseModel<CompanyModel> {
        try {
            val response = withContext(Dispatchers.IO) {
                stockFieldApi.getCompanies()
            }

            return ListResponseModel(
                totalCounts = response.totalCounts,
                list = response.list.map {
                    companyMapper.mapFromEntity(it)
                }
            )
        } catch (exception: Exception) {
            throw exception
        }
    }
}