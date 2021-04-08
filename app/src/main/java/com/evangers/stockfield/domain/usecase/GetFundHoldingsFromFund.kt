package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFundHoldingsFromFund @Inject constructor(
    private val fundRepositoryImpl: FundRepository
) : FlowUseCase<GetFundHoldingsFromFund.Request, GetFundHoldingsFromFund.Response> {
    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val response = fundRepositoryImpl.getFundComparison(
                fund = request.fundName,
                itemsPerPage = 100
            )
            emit(
                Response.Success(
                    total = response.totalCounts,
                    fundHoldings = response.list
                )
            )
        } catch (e: Exception) {
            emit(Response.Failure(e.message.toString()))
        }
    }

    class Request(
        val fundName: String
    )

    sealed class Response {
        class Success(
            val total: Int,
            val fundHoldings: List<FundHoldingComparisonModel>
        ) : Response()

        class Failure(val errorMessage: String) : Response()
    }
}