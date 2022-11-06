package com.ahmetturk.definex.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetturk.definex.network.NetworkResult
import com.ahmetturk.definex.network.RetrofitBuilder.apiService
import com.ahmetturk.definex.network.apiCall
import com.ahmetturk.definex.network.main.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _firstProductList = MutableStateFlow(emptyList<Product>())
    val firstProductList = _firstProductList.asStateFlow()

    private val _secondProductList = MutableStateFlow(emptyList<Product>())
    val secondProductList = _secondProductList.asStateFlow()

    private val _thirdProductList = MutableStateFlow(emptyList<Product>())
    val thirdProductList = _thirdProductList.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        fetchLists()
    }

    private fun fetchLists() = viewModelScope.launch {
        _loading.value = true
        listOf(
            launch {
                when (val response = apiCall { apiService.discoverFirstHorizontalList() }) {
                    is NetworkResult.Error -> {}
                    is NetworkResult.Success -> {
                        _firstProductList.value = response.value.list
                    }
                }
            },
            launch {
                when (val response = apiCall { apiService.discoverSecondHorizontalList() }) {
                    is NetworkResult.Error -> {}
                    is NetworkResult.Success -> {
                        _secondProductList.value = response.value.list
                    }
                }
            },
            launch {
                when (val response = apiCall { apiService.discoverThirthTwoColumnList() }) {
                    is NetworkResult.Error -> {}
                    is NetworkResult.Success -> {
                        _thirdProductList.value = response.value.list
                    }
                }
            }).joinAll()
        _loading.value = false
    }

    fun pullToRefresh() {
        fetchLists()
    }

}
