package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.mapper.CompanyMapper
import com.evangers.stockfield.domain.model.CompanyFundsModel
import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val companyMapper: CompanyMapper
) : CompanyRepository {
    override suspend fun getCompanies(): List<CompanyModel> {
        // TODO: 2/21/21 api 추가 할 것
        return listOf(
            CompanyModel("캐시우드 누님"),
            CompanyModel("버핏 형님"),
            CompanyModel("헌진"),
            CompanyModel("단기"),
            CompanyModel("진형"),
            CompanyModel("선국")
        )
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