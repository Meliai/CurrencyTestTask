package com.currency.android.data.di

import com.currency.android.data.features.feature1.datasource.TestDataSource
import com.currency.android.data.features.feature1.datasource.TestRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Suppress("TooManyFunctions")
@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindTestDataSource(source: TestRemoteDataSource): TestDataSource
}