package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFundListFromTicker @Inject constructor(
    private val fundRepository: FundRepository
) : FlowUseCase<GetFundListFromTicker.Request, GetFundListFromTicker.Response> {

    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val response = fundRepository.getFundsFromTicker(request.ticker)
            emit(
                Response.Success(
                    total = response.totalCounts,
                    funds = response.list
                )
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(Response.Failure(e))
        }
    }

    class Request(
        val ticker: String,
    )

    sealed class Response {
        class Success(
            val total: Int,
            val funds: List<FundModel>
        ) : Response()

        class Failure(
            val exception: Exception
        ) : Response()

    }
}