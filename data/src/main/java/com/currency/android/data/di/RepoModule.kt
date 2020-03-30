package com.currency.android.data.di

import com.currency.android.data.features.currency.repository.TestDataRepository
import com.currency.android.domain.features.feature1.repository.TestRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
@Suppress("TooManyFunctions")
interface RepoModule {

    @Binds
    @Singleton
    fun bindTestRepository(repo: TestDataRepository): TestRepository
}