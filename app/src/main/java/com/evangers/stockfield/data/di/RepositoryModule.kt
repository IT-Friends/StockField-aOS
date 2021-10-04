package com.evangers.stockfield.data.di

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.CompanyMapper
import com.evangers.stockfield.data.mapper.FundHoldingsMapper
import com.evangers.stockfield.data.mapper.FundMapper
import com.evangers.stockfield.data.mapper.HistoryMapper
import com.evangers.stockfield.data.repository.CompanyRepositoryImpl
import com.evangers.stockfield.data.repository.FundRepositoryImpl
import com.evangers.stockfield.data.repository.StockRepositoryImpl
import com.evangers.stockfield.domain.repository.CompanyRepository
import com.evangers.stockfield.domain.repository.FundRepository
import com.evangers.stockfield.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFundRepository(
        stockFieldApi: StockFieldApi,
        fundHoldingsMapper: FundHoldingsMapper,
        fundMapper: FundMapper,
        historyMapper: HistoryMapper
    ): FundRepository {
        return FundRepositoryImpl(
            stockFieldApi,
            fundHoldingsMapper,
            fundMapper,
            historyMapper
        )
    }

    @Singleton
    @Provides
    fun provideCompanyRepository(
        stockFieldApi: StockFieldApi,
        companyMapper: CompanyMapper
    ): CompanyRepository {
        return CompanyRepositoryImpl(
            stockFieldApi,
            companyMapper
        )
    }

    @Singleton
    @Provides
    fun provideStockRepository(
        stockFieldApi: StockFieldApi
    ): StockRepository {
        return StockRepositoryImpl(
            stockFieldApi
        )
    }

    @Singleton
    @Provides
    fun provideServerStateRepository(
    ): IServerStateRepository {
        return ServerStateRepositoryImpl()
    }

}