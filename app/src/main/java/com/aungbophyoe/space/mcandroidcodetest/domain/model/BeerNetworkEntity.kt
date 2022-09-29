package com.aungbophyoe.space.mcandroidcodetest.domain.model

import com.squareup.moshi.Json

data class BeerNetworkEntity(
    @Json(name = "id")
    val id: Int,
    @Json(name = "description")
    val description: String?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "tagline")
    val tagline: String?
)