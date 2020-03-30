package com.currency.android.data.features.currency.dto

import com.google.gson.annotations.SerializedName

data class CurrencyDataDto(
    @SerializedName("baseCurrency") val defaultCurrency: String,
    @SerializedName("rates") val rates: HashMap<String, Double>
)