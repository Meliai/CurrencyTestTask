package com.currency.android.presentation.di

import com.currency.android.presentation.features.currency_list.ui.CurrencyListFragment
import com.currency.android.presentation.core.di.FragmentScope
import com.currency.android.presentation.features.currency_list.di.CurrencyListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [CurrencyListModule::class])
    abstract fun bindCurrencyListFragment(): CurrencyListFragment
}