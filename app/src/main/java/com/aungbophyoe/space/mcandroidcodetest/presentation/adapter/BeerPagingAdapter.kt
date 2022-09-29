package com.aungbophyoe.space.mcandroidcodetest.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mcandroidcodetest.databinding.RvBeerItemBinding
import com.aungbophyoe.space.mcandroidcodetest.domain.model.Beer
import com.aungbophyoe.space.mcandroidcodetest.utility.ImageBinderAdapter

class BeerPagingAdapter(private val context: Context) : PagingDataAdapter<Beer,BeerPagingAdapter.ViewHolder>(DiffUtils) {

    class ViewHolder(private val binding: RvBeerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(beerItem: Beer){
            binding.apply {
                ImageBinderAdapter.setImageUrl(ivImage,beerItem.imageUrl)
                tvName.text = beerItem.name
                tvDescription.text = beerItem.description
                tvTagline.text = beerItem.tagline
            }
        }
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    object DiffUtils : DiffUtil.ItemCallback<Beer>(){
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beerItem = getItem(position)
        if(beerItem!=null){
            holder.bind(beerItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(RvBeerItemBinding.inflate(inflater,parent,false))
        return viewHolder
    }
}