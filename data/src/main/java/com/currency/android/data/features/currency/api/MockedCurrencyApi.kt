package com.currency.android.data.features.currency.api

import com.currency.android.data.features.currency.dto.CurrencyDataDto
import io.reactivex.Single

@Suppress("MagicNumber")
class MockedCurrencyApi : CurrencyApi {
    override fun getLatestCurrencyData(baseCurrency: String): Single<CurrencyDataDto> =
        Single.just(CurrencyDataDto(
            defaultCurrency = "EUR",
            rates = hashMapOf(
                "CHF" to 1.152,
                "BRL" to 4.251,
                "CAD" to 1.504
            )
        ))
}