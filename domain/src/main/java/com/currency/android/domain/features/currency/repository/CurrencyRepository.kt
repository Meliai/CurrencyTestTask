package com.currency.android.domain.features.currency.repository

import com.currency.android.domain.features.currency.model.CurrencyModel
import io.reactivex.Single

interface CurrencyRepository {

    fun getLatestCurrencyData(defaultCurrency: String): Single<List<CurrencyModel>>
}