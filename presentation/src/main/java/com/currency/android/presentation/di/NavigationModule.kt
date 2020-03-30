package com.currency.android.presentation.di

import com.currency.android.presentation.core.navigation.FlowRouter
import com.currency.android.presentation.core.navigation.LocalCiceroneHolder
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    private var cicerone: Cicerone<Router> = Cicerone.create(FlowRouter(null))

    @Provides
    @Singleton
    fun provideRouter(): FlowRouter = cicerone.router as FlowRouter

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun provideLocalCiceroneHolder(): LocalCiceroneHolder = LocalCiceroneHolder()
}