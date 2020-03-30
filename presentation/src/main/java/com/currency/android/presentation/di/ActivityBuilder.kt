package com.currency.android.presentation.di

import com.currency.android.presentation.features.app.ui.AppActivity
import com.currency.android.presentation.core.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    fun bindAppActivity(): AppActivity
}