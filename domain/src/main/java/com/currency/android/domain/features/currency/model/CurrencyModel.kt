package com.currency.android.domain.features.currency.model

data class CurrencyModel(
    val currency: String,
    val value: Double,
    val isDefault: Boolean
)