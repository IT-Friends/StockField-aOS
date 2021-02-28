package com.evangers.stockfield.data.mapper

import com.evangers.stockfield.data.entity.FundHoldingsEntity
import com.evangers.stockfield.data.entity.FundHoldingEntity
import com.evangers.stockfield.domain.model.FundHoldingsModel
import com.evangers.stockfield.domain.model.FundHoldingModel
import javax.inject.Inject

class FundHoldingsMapper @Inject constructor() : EntityMapper<FundHoldingsEntity, FundHoldingsModel> {

    override fun mapFromEntity(holdingsEntity: FundHoldingsEntity): FundHoldingsModel {
        return FundHoldingsModel(
            totalCounts = holdingsEntity.totalCounts,
            fundHoldingList = holdingsEntity.fundHoldingList.map {
                FundHoldingModel(
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

    override fun mapToEntity(domainHoldingsModel: FundHoldingsModel): FundHoldingsEntity {
        return FundHoldingsEntity(
            totalCounts = domainHoldingsModel.totalCounts,
            fundHoldingList = domainHoldingsModel.fundHoldingList.map {
                FundHoldingEntity(
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