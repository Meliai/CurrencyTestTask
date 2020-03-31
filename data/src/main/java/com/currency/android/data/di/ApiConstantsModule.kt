package com.currency.android.data.di

import com.currency.android.data.core.qualifires.ServerUrl
import dagger.Module
import dagger.Provides

@Module
@Suppress("FunctionOnlyReturningConstant")
class ApiConstantsModule(private val isDebugMode: Boolean) {

    @Provides
    @ServerUrl
    fun provideServerUrl() = when (isDebugMode) {
        true -> SERVER_URL_TEST
        else -> SERVER_URL_PROD
    }

    private companion object {
        const val SERVER_URL_PROD = "https://hiring.revolut.codes/api/android"
        const val SERVER_URL_TEST = "https://hiring.revolut.codes/api/android"
    }
}