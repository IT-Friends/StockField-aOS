package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.model.ListResponseModel

interface CompanyRepository {
    suspend fun getCompanies(): ListResponseModel<CompanyModel>
}