package com.currency.android.domain.features.currency.model

data class CurrencyModel(
    val currency: String,
    val rate: Double,
    val isDefault: Boolean,
    val multiplier: Double = 100.0
)