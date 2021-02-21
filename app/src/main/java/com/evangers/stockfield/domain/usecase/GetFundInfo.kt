package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFundInfo @Inject constructor(
    private val fundRepositoryImpl: FundRepository
) : FlowUseCase<GetFundInfo.Request, GetFundInfo.Response> {
    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val fund = fundRepositoryImpl.getFund(request.fundName)
            emit(Response.Success(fund))
        } catch (e: Exception) {
            emit(Response.Failure(e.message.toString()))
        }
    }

    class Request(val fundName: String)

    sealed class Response {
        class Success(val fund: FundModel) : Response()
        class Failure(val errorMessage: String) : Response()
    }
}