package com.evangers.stockfield.data.mapper

import com.evangers.stockfield.data.entity.FundEntity
import com.evangers.stockfield.domain.model.FundModel
import javax.inject.Inject

class FundMapper @Inject constructor(
    private val fundHoldingsMapper: FundHoldingsMapper
) : EntityMapper<FundEntity, FundModel> {
    override fun mapFromEntity(entity: FundEntity): FundModel {
        return FundModel(
            id = entity.id,
            name = entity.name,
            companyIconUrl = entity.companyIconUrl ?: "",
            companyName = entity.companyName ?: "",
            fundHoldingComparisons = entity.fundHoldingComparisons?.map {
                fundHoldingsMapper.mapFromEntity(
                    it
                )
            } ?: emptyList()
        )
    }

    override fun mapToEntity(domainModel: FundModel): FundEntity {
        return FundEntity(
            id = domainModel.id,
            name = domainModel.name,
            companyIconUrl = domainModel.companyIconUrl ?: "",
            companyName = domainModel.companyName ?: "",
            fundHoldingComparisons = domainModel.fundHoldingComparisons?.map {
                fundHoldingsMapper.mapToEntity(
                    it
                )
            } ?: emptyList()
        )
    }
}