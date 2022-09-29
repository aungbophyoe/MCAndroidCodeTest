package com.aungbophyoe.space.mcandroidcodetest.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mcandroidcodetest.data.remote.paging.BeersPagingSource
import com.aungbophyoe.space.mcandroidcodetest.databinding.ActivityMainBinding
import com.aungbophyoe.space.mcandroidcodetest.presentation.adapter.BeerPagingAdapter
import com.aungbophyoe.space.mcandroidcodetest.presentation.adapter.BeersLoadStateAdapter
import com.aungbophyoe.space.mcandroidcodetest.presentation.viewmodels.MainViewModel
import com.aungbophyoe.space.mcandroidcodetest.utility.ViewModelFactory
import com.aungbophyoe.space.mcandroidcodetest.utility.isNetworkAvailable
import com.aungbophyoe.space.mcandroidcodetest.utility.showOrGone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),BeersLoadStateAdapter.RetryOnClickOnListener {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding
    @Inject lateinit var beersPagingSource: BeersPagingSource
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this,ViewModelFactory(beersPagingSource))[MainViewModel::class.java]
    }
    private val beerPagingAdapter : BeerPagingAdapter by lazy {
        BeerPagingAdapter(this)
    }
    private val beersLoadStateAdapter : BeersLoadStateAdapter by lazy {
        BeersLoadStateAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding!!.root
        setContentView(view)
        intiRecyclerView()
        observeData()
        binding!!.apply {
            rlTryAgain.setOnClickListener {
                if(isNetworkAvailable(this@MainActivity)){
                    beerPagingAdapter.retry()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding!!.apply {
            if(isNetworkAvailable(this@MainActivity)){
                rlTryAgain.showOrGone(false)
            }else{
                rlTryAgain.showOrGone(true)
            }
        }
    }

    private fun observeData(){
        binding!!.apply {
            lifecycleScope.launchWhenStarted {
                try {
                    viewModel.getBeers.collectLatest { data ->
                        beerPagingAdapter.submitData(data)
                    }
                }catch (e:Exception){
                    Log.d("error","${e.message}")
                    viewModel.getBeers.cancellable()
                }
            }
        }
    }

    private fun intiRecyclerView() {
        binding!!.apply {
            rvBeers.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = beerPagingAdapter.withLoadStateFooter(beersLoadStateAdapter)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun retryOnClick() {
        if(isNetworkAvailable(this)){
            beerPagingAdapter.retry()
        }
    }
}