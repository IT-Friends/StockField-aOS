package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.CompanyFundsModel
import com.evangers.stockfield.domain.model.CompanyModel

interface CompanyRepository {
    suspend fun getCompanies(): List<CompanyModel>
    suspend fun getFundsFromCompany(companyIndex: Int): CompanyFundsModel
}