package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFundListFromCompany @Inject constructor(
    private val fundRepository: FundRepository
) : FlowUseCase<GetFundListFromCompany.Request, GetFundListFromCompany.Response> {

    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val response = fundRepository.getFundsFromCompany(request.companyIndex)
            emit(
                Response.Success(
                    total = response.totalCounts,
                    funds = response.list
                )
            )
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }

    }

    class Request(
        val companyIndex: Int
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