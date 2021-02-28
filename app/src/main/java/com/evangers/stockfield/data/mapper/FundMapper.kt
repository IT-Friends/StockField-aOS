package com.evangers.stockfield.data.mapper

import com.evangers.stockfield.data.entity.FundEntity
import com.evangers.stockfield.domain.model.FundModel
import javax.inject.Inject

class FundMapper @Inject constructor(
) : EntityMapper<FundEntity, FundModel> {
    override fun mapFromEntity(entity: FundEntity): FundModel {
        return FundModel(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: FundModel): FundEntity {
        return FundEntity(
            id = domainModel.id,
            name = domainModel.name
        )
    }
}