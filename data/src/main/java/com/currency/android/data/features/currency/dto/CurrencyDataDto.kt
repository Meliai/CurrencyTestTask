package com.currency.android.data.features.currency.dto

data class CurrencyDataDto(
    val baseCurrency: String,
    val rates: HashMap<String, Double>
)