package com.ahmetturk.definex.network.main

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Price,
    @SerializedName("old_price") val oldPrice: Price?,
    @SerializedName("discount") val discount: String,
    @SerializedName("rate_percentage") val ratePercentage: Int?,
)
