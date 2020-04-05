package com.currency.android.domain.features.currency.repository

import com.currency.android.domain.features.currency.model.CurrencyModel
import io.reactivex.Observable

interface CurrencyRepository {

    fun getLatestCurrencyData(baseCurrency: String): Observable<List<CurrencyModel>>
}