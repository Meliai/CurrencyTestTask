package com.currency.android.data.di

import com.currency.android.data.features.currency.dto.CurrencyDataDto
import com.currency.android.data.features.currency.mapper.TestDtoMapper
import com.currency.android.domain.features.feature1.model.TestModel
import com.currency.common.mapper.Mapper
import dagger.Binds
import dagger.Module

@Module
interface MappersModule {

    @Binds
    fun bindTestDtoMapper(mapper: TestDtoMapper): Mapper<CurrencyDataDto, TestModel>
}