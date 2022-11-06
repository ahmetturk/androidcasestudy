package com.ahmetturk.definex.screens.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ahmetturk.definex.base.BaseFragment
import com.ahmetturk.definex.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    private val firstAdapter by lazy { MainAdapter(horizontalSpanCount = 2) }
    private val secondAdapter by lazy { MainAdapter(horizontalSpanCount = 3) }
    private val thirdAdapter by lazy { MainAdapter() }

    override fun getViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstRecyclerView.adapter = firstAdapter
        binding.secondRecyclerView.adapter = secondAdapter
        binding.thirdRecyclerView.adapter = thirdAdapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.pullToRefresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.firstProductList.collect {
                        firstAdapter.setList(it)
                    }
                }

                launch {
                    viewModel.secondProductList.collect {
                        secondAdapter.setList(it)
                    }
                }

                launch {
                    viewModel.thirdProductList.collect {
                        thirdAdapter.setList(it)
                    }
                }

                launch {
                    viewModel.loading.collect {
                        binding.swipeRefresh.isRefreshing = it
                    }
                }
            }
        }
    }

}
