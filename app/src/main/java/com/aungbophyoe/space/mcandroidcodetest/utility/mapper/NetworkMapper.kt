package com.aungbophyoe.space.mcandroidcodetest.utility.mapper
import com.aungbophyoe.space.mcandroidcodetest.domain.model.Beer
import com.aungbophyoe.space.mcandroidcodetest.domain.model.BeerNetworkEntity
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<BeerNetworkEntity,Beer> {
    override fun mapFromEntity(entity: BeerNetworkEntity): Beer {
        return Beer(entity.id,entity.description ?: "",entity.imageUrl ?: "https://picsum.photos/200",entity.name ?: "",entity.tagline ?: "")
    }

    override fun mapToEntity(domainModel: Beer): BeerNetworkEntity {
        return BeerNetworkEntity(domainModel.id,domainModel.description,domainModel.imageUrl,domainModel.name,domainModel.tagline)
    }

    fun mapFromEntityList(entities : List<BeerNetworkEntity>) : List<Beer>{
        val list = entities.map {
            mapFromEntity(it)
        }
        return list
    }

}