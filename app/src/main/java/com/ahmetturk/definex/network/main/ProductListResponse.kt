package com.ahmetturk.definex.network.main

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("list") val list: List<Product>,
)
