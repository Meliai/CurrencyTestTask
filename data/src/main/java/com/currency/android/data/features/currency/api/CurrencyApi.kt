package com.currency.android.data.features.currency.api

import com.currency.android.data.features.currency.dto.CurrencyDataDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("api/android/latest")
    fun getLatestCurrencyData(
        @Query("base") baseCurrency: String
    ): Single<CurrencyDataDto>
}
