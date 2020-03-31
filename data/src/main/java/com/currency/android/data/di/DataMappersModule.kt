package com.currency.android.data.di

import com.currency.android.data.features.currency.dto.CurrencyDataDto
import com.currency.android.data.features.currency.mapper.CurrencyDataDtoMapper
import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.common.mapper.Mapper
import dagger.Binds
import dagger.Module

@Module
interface DataMappersModule {

    @Binds
    fun bindCurrencyDataDtoMapper(
        mapper: CurrencyDataDtoMapper
    ): Mapper<CurrencyDataDto, List<CurrencyModel>>
}