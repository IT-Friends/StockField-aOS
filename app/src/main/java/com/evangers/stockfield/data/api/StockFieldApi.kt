package com.evangers.stockfield.data.api

import com.evangers.stockfield.data.entity.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockFieldApi {
    @GET("funds/{fundName}/comparison")
    suspend fun getFundComparison(
        @Path(value = "fundName") fundName: String,
        @Query(value = "date_from") dateFrom: String? = null,
        @Query(value = "date_to") dateTo: String? = null,
        @Query(value = "order") order: String = "ranking",
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int?,
    ): ListResponseEntity<FundHoldingComparisonEntity>

    @GET("companies")
    suspend fun getCompanies(
    ): ListResponseEntity<CompanyEntity>

    @GET("funds")
    suspend fun getFundsFromCompany(
        @Query(value = "company_id") companyId: Int
    ): ListResponseEntity<FundEntity>

    @GET("funds/{fundName}/fund_holdings/{ticker}/history")
    suspend fun getHistoryFromFund(
        @Path(value = "fundName") fundName: String,
        @Path(value = "ticker") ticker: String,
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int?
    ): ListResponseEntity<HistoryEntity>

    @GET("stocks/{ticker}")
    suspend fun getStock(
        @Path(value = "ticker") ticker: String
    ): StockEntity
}