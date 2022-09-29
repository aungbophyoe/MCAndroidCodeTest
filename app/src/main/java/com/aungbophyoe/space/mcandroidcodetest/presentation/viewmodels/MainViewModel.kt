package com.aungbophyoe.space.mcandroidcodetest.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aungbophyoe.space.mcandroidcodetest.domain.model.Beer
import com.aungbophyoe.space.mcandroidcodetest.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val state : Flow<PagingData<Beer>> = getBeers()
    private fun getBeers() : Flow<PagingData<Beer>>{
        return repository.getBeers().cachedIn(viewModelScope)
    }
}