package com.evangers.stockfield.data.mapper

import com.evangers.stockfield.data.entity.FundHoldingComparisonEntity
import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import javax.inject.Inject

class FundHoldingsMapper @Inject constructor() :
    EntityMapper<FundHoldingComparisonEntity, FundHoldingComparisonModel> {
    override fun mapFromEntity(entity: FundHoldingComparisonEntity): FundHoldingComparisonModel {
        return FundHoldingComparisonModel(
            rank = entity.rank,
            displayName = entity.displayName?:"",
            rankDifference = entity.rankDifference,
            ticker = entity.ticker,
            shares = entity.shares,
            sharesDifference = entity.sharesDifference,
            weight = entity.weight,
            weightDifference = entity.weightDifference,
            closingPrice = entity.closingPrice,
            closingPriceDifference = entity.closingPriceDifference,
            closingPriceChangePercent = entity.closingPriceChangePercent,
            dateTo = entity.dateTo,
            dateFrom = entity.dateFrom
        )
    }

    override fun mapToEntity(domainModel: FundHoldingComparisonModel): FundHoldingComparisonEntity {
        return FundHoldingComparisonEntity(
            rank = domainModel.rank,
            displayName = domainModel.displayName,
            rankDifference = domainModel.rankDifference,
            ticker = domainModel.ticker,
            shares = domainModel.shares,
            sharesDifference = domainModel.sharesDifference,
            weight = domainModel.weight,
            weightDifference = domainModel.weightDifference,
            closingPrice = domainModel.closingPrice,
            closingPriceDifference = domainModel.closingPriceDifference,
            closingPriceChangePercent = domainModel.closingPriceChangePercent,
            dateTo = domainModel.dateTo,
            dateFrom = domainModel.dateFrom
        )
    }

}