package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.HistoryModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFundHistoryWithStock @Inject constructor(
    private val fundRepositoryImpl: FundRepository
) : FlowUseCase<GetFundHistoryWithStock.Request, GetFundHistoryWithStock.Response> {

    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val response = fundRepositoryImpl.getFundHistory(
                page = request.page,
                ticker = request.ticker,
                fundName = request.fundName
            )

            emit(
                Response.Success(
                    total = response.totalCounts,
                    list = response.list
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Response.Failure(e))
        }

    }

    class Request(
        val page: Int,
        val ticker: String,
        val fundName: String
    )

    sealed class Response {
        class Success(
            val total: Int,
            val list: List<HistoryModel>
        ) : Response()

        class Failure(
            val throwable: Throwable
        ) : Response()
    }
}