package com.aungbophyoe.space.mcandroidcodetest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.aungbophyoe.space.mcandroidcodetest.data.remote.paging.BeersPagingSource
import com.aungbophyoe.space.mcandroidcodetest.utility.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val beersPagingSource: BeersPagingSource) : ViewModel() {
    val getBeers = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = Constants.PER_PAGE)
    ) {
        beersPagingSource
    }.flow.cachedIn(viewModelScope)
}