package com.aungbophyoe.space.mcandroidcodetest.utility.mapper

interface EntityMapper<Entity,DomainModel> {
    fun mapFromEntity(entity: Entity) : DomainModel
    fun mapToEntity(domainModel: DomainModel) : Entity
}