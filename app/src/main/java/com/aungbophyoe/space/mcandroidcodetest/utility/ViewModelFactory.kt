package com.aungbophyoe.space.mcandroidcodetest.utility


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aungbophyoe.space.mcandroidcodetest.data.remote.paging.BeersPagingSource
import com.aungbophyoe.space.mcandroidcodetest.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val beersPagingSource: BeersPagingSource) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(beersPagingSource) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}