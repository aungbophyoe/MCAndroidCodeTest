package com.aungbophyoe.space.mcandroidcodetest.utility

import android.content.Context
import android.net.ConnectivityManager
import android.view.View

fun View.showOrGone(show: Boolean) {
    visibility = if(show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.showOrInvisible(show: Boolean) {
    visibility = if(show) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}