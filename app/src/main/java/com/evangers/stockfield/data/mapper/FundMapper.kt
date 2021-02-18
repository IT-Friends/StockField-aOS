package com.evangers.stockfield.data.mapper

import com.evangers.stockfield.data.entity.FundEntity
import com.evangers.stockfield.data.entity.StockEntity
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.model.StockModel
import javax.inject.Inject

class FundMapper @Inject constructor() : EntityMapper<FundEntity, FundModel> {

    override fun mapFromEntity(entity: FundEntity): FundModel {
        return FundModel(
            fundName = entity.fundName,
            stockList = entity.stockList.map {
                StockModel(
                    rank = it.rank,
                    rankDifference = it.rankDifference,
                    ticker = it.ticker,
                    shares = it.shares,
                    sharesDifference = it.sharesDifference,
                    weight = it.weight,
                    weightDifference = it.weightDifference,
                    closingPrice = it.closingPrice,
                    closingPriceDifference = it.closingPriceDifference,
                    dateTo = it.dateTo,
                    dateFrom = it.dateFrom
                )
            }
        )
    }

    override fun mapToEntity(domainModel: FundModel): FundEntity {
        return FundEntity(
            fundName = domainModel.fundName,
            stockList = domainModel.stockList.map {
                StockEntity(
                    rank = it.rank,
                    rankDifference = it.rankDifference,
                    ticker = it.ticker,
                    shares = it.shares,
                    sharesDifference = it.sharesDifference,
                    weight = it.weight,
                    weightDifference = it.weightDifference,
                    closingPrice = it.closingPrice,
                    closingPriceDifference = it.closingPriceDifference,
                    dateTo = it.dateTo,
                    dateFrom = it.dateFrom
                )
            }
        )
    }

}