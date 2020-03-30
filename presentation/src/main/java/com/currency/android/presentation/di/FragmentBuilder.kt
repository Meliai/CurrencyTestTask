package com.currency.android.presentation.di

import com.currency.android.presentation.features.currency_list.ui.CurrencyListFragment
import com.currency.android.presentation.core.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindCurrencyListFragment(): CurrencyListFragment
}