package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.StockModel
import com.evangers.stockfield.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStockDetail @Inject constructor(
    private val stockRepository: StockRepository
) : FlowUseCase<GetStockDetail.Request, GetStockDetail.Response> {


    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val response = stockRepository.getStock(request.ticker)
            emit(
                Response.Success(
                    stockModel = response
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Response.Failure(e))
        }
    }

    class Request(
        val ticker: String
    )

    sealed class Response {

        class Success(
            val stockModel: StockModel
        ) : Response()

        class Failure(
            val throwable: Throwable
        ) : Response()
    }
}