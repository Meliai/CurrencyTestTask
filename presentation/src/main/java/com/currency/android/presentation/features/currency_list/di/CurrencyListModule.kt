package com.currency.android.presentation.features.currency_list.di

import com.currency.android.presentation.core.di.FragmentScope
import com.currency.android.presentation.features.currency_list.ui.adapter.CurrencyRateListDelegatesFactory
import com.nullgr.core.adapter.AdapterDelegatesFactory
import com.nullgr.core.adapter.DynamicAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [CurrencyListModule.Declarations::class])
class CurrencyListModule {

    @Module
    interface Declarations {
        @Binds
        @FragmentScope
        fun delegatesFactory(factory: CurrencyRateListDelegatesFactory): AdapterDelegatesFactory
    }

    @Provides
    @FragmentScope
    fun dynamicAdapter(factory: AdapterDelegatesFactory): DynamicAdapter = DynamicAdapter(factory)
}