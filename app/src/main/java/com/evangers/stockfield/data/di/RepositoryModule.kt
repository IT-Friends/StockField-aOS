package com.evangers.stockfield.data.di

import android.content.SharedPreferences
import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.CompanyMapper
import com.evangers.stockfield.data.mapper.FundHoldingsMapper
import com.evangers.stockfield.data.mapper.FundMapper
import com.evangers.stockfield.data.mapper.HistoryMapper
import com.evangers.stockfield.data.repository.*
import com.evangers.stockfield.data.util.SharedPreference
import com.evangers.stockfield.data.util.SharedPreferenceImpl
import com.evangers.stockfield.domain.repository.*
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
    fun provideSharedPreference(
        sharedPreferences: SharedPreferences
    ): SharedPreference = SharedPreferenceImpl(sharedPreferences)

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


    @Singleton
    @Provides
    fun provideUserRepository(
        sharedPreference: SharedPreference
    ): IUserRepository = UserRepositoryImpl(sharedPreference)
}