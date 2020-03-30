package com.currency.android.data.di

import com.currency.android.data.features.currency.repository.CurrencyDataRepository
import com.currency.android.domain.features.currency.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepoModule {

    @Binds
    @Singleton
    fun bindCurrencyRepository(repo: CurrencyDataRepository): CurrencyRepository
}