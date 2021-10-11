package com.evangers.stockfield.data.mapper

import com.evangers.stockfield.data.entity.HistoryEntity
import com.evangers.stockfield.domain.model.HistoryModel
import javax.inject.Inject

class HistoryMapper @Inject constructor(
) : EntityMapper<HistoryEntity, HistoryModel> {
    override fun mapFromEntity(entity: HistoryEntity): HistoryModel {
        val properText = Regex("\\d{4}-\\d{2}-\\d{2}").find(entity.date, 0)
        return HistoryModel(
            date = properText?.value ?: "",
            shares = entity.shares,
            sharesDifference = entity.sharesDifference,
            weight = entity.weight,
            weightDifference = entity.weightDifference
        )
    }

    override fun mapToEntity(domainModel: HistoryModel): HistoryEntity {
        return HistoryEntity(
            date = domainModel.date,
            shares = domainModel.shares,
            sharesDifference = domainModel.sharesDifference,
            weight = domainModel.weight,
            weightDifference = domainModel.weightDifference
        )
    }
}