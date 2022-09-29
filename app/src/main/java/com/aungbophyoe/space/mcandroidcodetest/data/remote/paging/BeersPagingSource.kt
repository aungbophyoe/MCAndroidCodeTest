package com.aungbophyoe.space.mcandroidcodetest.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aungbophyoe.space.mcandroidcodetest.data.remote.ApiService
import com.aungbophyoe.space.mcandroidcodetest.domain.model.Beer
import com.aungbophyoe.space.mcandroidcodetest.utility.Constants
import com.aungbophyoe.space.mcandroidcodetest.utility.mapper.NetworkMapper
import retrofit2.HttpException


class BeersPagingSource constructor(
    private val apiService: ApiService,
    private val networkMapper: NetworkMapper
) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = apiService.getAllBeers(nextPageNumber,Constants.PER_PAGE)
            val data  = networkMapper.mapFromEntityList(response)

            return LoadResult.Page(
                data = data,
                prevKey = if(nextPageNumber == 1) null else -1,
                nextKey = if(nextPageNumber < 18) nextPageNumber.plus(1) else null// response.nextKey or total page no from response
            )
        } catch (e: Exception) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

}