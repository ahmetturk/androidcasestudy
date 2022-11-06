package com.ahmetturk.definex.network.main

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("value") val value: Double,
    @SerializedName("currency") val currency: String,
)
