package com.currency.android.data.features.currency.datasource

import com.currency.android.data.features.currency.dto.CurrencyDataDto
import io.reactivex.Observable

interface CurrencyDataSource {

    fun getCurrencyData(baseCurrency: String): Observable<CurrencyDataDto>
}