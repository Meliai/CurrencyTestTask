package com.currency.android.data.di

import com.currency.android.data.features.currency.datasource.CurrencyDataSource
import com.currency.android.data.features.currency.datasource.CurrencyRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Suppress("TooManyFunctions")
@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindTestDataSource(source: CurrencyRemoteDataSource): CurrencyDataSource
}