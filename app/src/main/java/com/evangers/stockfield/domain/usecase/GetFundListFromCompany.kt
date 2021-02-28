package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.CompanyFundsModel
import com.evangers.stockfield.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFundListFromCompany @Inject constructor(
    private val companyRepository: CompanyRepository
) : FlowUseCase<GetFundListFromCompany.Request, GetFundListFromCompany.Response> {

    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val funds = companyRepository.getFundsFromCompany(request.companyIndex)
            emit(Response.Success(funds))
        } catch (e: java.lang.Exception) {
            emit(Response.Failure(e))
        }

    }

    class Request(
        val companyIndex: Int
    )

    sealed class Response {

        class Success(
            val funds: CompanyFundsModel
        ) : Response()

        class Failure(
            val exception: Exception
        ) : Response()

    }
}