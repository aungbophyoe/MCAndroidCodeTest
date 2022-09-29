package com.aungbophyoe.space.mcandroidcodetest.utility

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.aungbophyoe.space.mcandroidcodetest.R

object ImageBinderAdapter {
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (url == null) {
            imageView.load(R.drawable.ic_default_image)
        } else {
            imageView.load(url){
                crossfade(true)
                placeholder(R.drawable.ic_default_image)
                transformations(RoundedCornersTransformation(5f))
            }
        }
    }
}