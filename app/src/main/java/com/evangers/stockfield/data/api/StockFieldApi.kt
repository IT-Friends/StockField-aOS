package com.evangers.stockfield.data.api

import com.evangers.stockfield.data.entity.CompanyEntity
import com.evangers.stockfield.data.entity.FundEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface StockFieldApi {
    @GET("funds/{fundName}/comparison")
    suspend fun getFundInfo(
        @Path(value = "fundName") fundName: String
    ): FundEntity

    @GET("companies")
    suspend fun getCompanies(
    ): List<CompanyEntity>


}