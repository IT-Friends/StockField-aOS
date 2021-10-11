package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCompanies @Inject constructor(
    private val companyRepository: CompanyRepository
) : FlowUseCase<GetCompanies.Request, GetCompanies.Response> {

    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val response = companyRepository.getCompanies()

            emit(
                Response.Success(
                    total = response.totalCounts,
                    companyList = response.list
                )
            )
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    class Request

    sealed class Response {
        class Success(
            val total: Int,
            val companyList: List<CompanyModel>
        ) : Response()

        class Failure(
            val exception: Exception
        ) : Response()

    }
}