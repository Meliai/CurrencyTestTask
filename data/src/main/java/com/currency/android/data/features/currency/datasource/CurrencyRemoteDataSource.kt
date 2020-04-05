package com.currency.android.data.features.currency.datasource

import com.currency.android.data.features.currency.api.CurrencyApi
import com.currency.android.data.features.currency.dto.CurrencyDataDto
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyRemoteDataSource @Inject constructor(
    private val api: CurrencyApi
) : CurrencyDataSource {

    override fun getCurrencyData(baseCurrency: String): Observable<CurrencyDataDto> =
        api.getLatestCurrencyData(baseCurrency)
}