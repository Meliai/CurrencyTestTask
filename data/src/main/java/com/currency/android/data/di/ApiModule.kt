package com.currency.android.data.di

import com.currency.android.data.features.currency.api.CurrencyApi
import com.currency.android.data.features.currency.api.MockedCurrencyApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideCurrencyApi(retrofit: Retrofit): CurrencyApi =
        when (ApiConfig.USE_MOCKED_CURRENCY_API) {
            true -> MockedCurrencyApi()
            else -> retrofit.create<CurrencyApi>(CurrencyApi::class.java)
        }

    object ApiConfig {
        const val USE_MOCKED_CURRENCY_API = false
    }
}