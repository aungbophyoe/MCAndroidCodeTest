package com.aungbophyoe.space.mcandroidcodetest.domain.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aungbophyoe.space.mcandroidcodetest.data.remote.ApiService
import com.aungbophyoe.space.mcandroidcodetest.data.remote.paging.BeersPagingSource
import com.aungbophyoe.space.mcandroidcodetest.domain.model.Beer
import com.aungbophyoe.space.mcandroidcodetest.utility.Constants
import com.aungbophyoe.space.mcandroidcodetest.utility.mapper.NetworkMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val networkMapper: NetworkMapper
) {
    fun getBeers() : Flow<PagingData<Beer>> {
        return Pager(
            PagingConfig(pageSize = Constants.PER_PAGE)
        ) {
            BeersPagingSource(apiService,networkMapper)
        }.flow
    }
}