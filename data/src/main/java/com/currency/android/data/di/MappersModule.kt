package com.currency.android.data.di

import com.currency.common.mapper.Mapper
import com.currency.android.data.features.feature1.dto.TestDto
import com.currency.android.data.features.feature1.mapper.TestDtoMapper
import com.currency.android.domain.features.feature1.model.TestModel
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
@Suppress("UnnecessaryAbstractClass", "TooManyFunctions")
abstract class MappersModule {

    @Binds
    @Singleton
    abstract fun bindTestDtoMapper(mapper: TestDtoMapper): Mapper<TestDto, TestModel>
}