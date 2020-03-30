package com.currency.android.presentation.di

import com.currency.android.presentation.features.app.pm.AppPm
import com.currency.android.presentation.features.currency_list.pm.CurrencyListPm
import com.currency.android.presentation.core.pm.PmKey
import com.currency.android.presentation.core.pm.factory.GeneralPmFactory
import com.currency.android.presentation.core.pm.factory.PmFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.dmdev.rxpm.PresentationModel

@Module
interface PmModule {

    @Binds
    fun viewModelFactory(factory: GeneralPmFactory): PmFactory

    @Binds
    @IntoMap
    @PmKey(AppPm::class)
    fun bindAppPm(pm: AppPm): PresentationModel

    @Binds
    @IntoMap
    @PmKey(CurrencyListPm::class)
    fun bindOnBoardingPm(pm: CurrencyListPm): PresentationModel
}