package com.aungbophyoe.space.mcandroidcodetest.utility


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aungbophyoe.space.mcandroidcodetest.domain.repository.MainRepository
import com.aungbophyoe.space.mcandroidcodetest.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}