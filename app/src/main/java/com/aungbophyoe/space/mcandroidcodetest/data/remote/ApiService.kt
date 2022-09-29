package com.aungbophyoe.space.mcandroidcodetest.data.remote

import com.aungbophyoe.space.mcandroidcodetest.domain.model.BeerNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")
    suspend fun  getAllBeers(@Query("page") page : Int,@Query("per_page")perPage : Int): List<BeerNetworkEntity>
}