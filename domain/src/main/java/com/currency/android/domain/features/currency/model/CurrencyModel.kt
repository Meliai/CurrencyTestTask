package com.currency.android.domain.features.currency.model

data class CurrencyModel(
    val currency: String,
    val rate: Double,
    val isDefault: Boolean = false,
    var multiplier: Double = 1.0
)