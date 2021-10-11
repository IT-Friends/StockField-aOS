package com.evangers.stockfield.data.mapper

import com.evangers.stockfield.data.entity.CompanyEntity
import com.evangers.stockfield.domain.model.CompanyModel
import javax.inject.Inject

class CompanyMapper @Inject constructor() : EntityMapper<CompanyEntity, CompanyModel> {
    override fun mapFromEntity(entity: CompanyEntity): CompanyModel {
        return CompanyModel(
            id = entity.id,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: CompanyModel): CompanyEntity {
        return CompanyEntity(
            id = domainModel.id,
            name = domainModel.name
        )
    }
}