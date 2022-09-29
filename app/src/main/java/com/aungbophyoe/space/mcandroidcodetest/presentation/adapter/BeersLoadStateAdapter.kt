package com.aungbophyoe.space.mcandroidcodetest.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mcandroidcodetest.databinding.LoadStateViewBinding
import com.aungbophyoe.space.mcandroidcodetest.utility.showOrGone

class BeersLoadStateAdapter(private val retryOnClickOnListener: RetryOnClickOnListener) : LoadStateAdapter<BeersLoadStateAdapter.LoadStateViewHolder>() {
    interface RetryOnClickOnListener{
        fun retryOnClick()
    }

    inner class LoadStateViewHolder(val binding: LoadStateViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.binding.apply {
            when(loadState){
                is LoadState.Error -> {
                    progressBar.showOrGone(false)
                    Log.d("load_error","${loadState.error}")
                    if(loadState.endOfPaginationReached){
                        rlTryAgain.showOrGone(false)
                    }else{
                        rlTryAgain.showOrGone(true)
                    }
                }
                is LoadState.Loading -> {
                    progressBar.showOrGone(true)
                    rlTryAgain.showOrGone(false)
                }
                is  LoadState.NotLoading -> {
                    progressBar.showOrGone(false)
                }
            }
            rlTryAgain.setOnClickListener {
                retryOnClickOnListener.retryOnClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStateViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}