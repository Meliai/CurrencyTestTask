package com.currency.android.presentation.di

import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.android.presentation.features.currency_list.builder.CurrencyRateListBuilder
import com.currency.common.mapper.Mapper
import com.nullgr.core.adapter.items.ListItem
import dagger.Binds
import dagger.Module

@Module
interface PresentationMappersModule {

    @Binds
    fun bindCurrencyRateListBuilder(
        mapper: CurrencyRateListBuilder
    ): Mapper<CurrencyModel, ListItem>
}