package com.evangers.stockfield.domain.usecase

import com.evangers.stockfield.domain.model.StockPriceModel
import com.evangers.stockfield.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStockPriceHistory @Inject constructor(
    private val stockRepositoryImpl: StockRepository
) : FlowUseCase<GetStockPriceHistory.Request, GetStockPriceHistory.Response> {

    override suspend fun invoke(request: Request): Flow<Response> = flow {
        try {
            val response = stockRepositoryImpl.getHistoryFromStock(
                ticker = request.ticker,
                page = request.page,
                perPage = request.perPage,
                order = request.order
            )

            emit(
                Response.Success(
                    total = response.totalCounts,
                    list = response.list
                )
            )
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    class Request(
        val ticker: String,
        val page: Int,
        val perPage: Int? = null,
        val order: String = "desc"  // asc
    )

    sealed class Response {
        class Success(
            val total: Int,
            val list: List<StockPriceModel>
        ) : Response()

        class Failure(
            val error: Throwable
        ) : Response()
    }
}