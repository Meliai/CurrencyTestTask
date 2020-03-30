package com.currency.android.presentation.features.app.pm

import com.currency.android.presentation.Screens
import com.currency.android.presentation.core.pm.BasePm
import com.currency.android.presentation.core.pm.ServiceFacade
import com.currency.android.presentation.core.pm.listeners.ConnectionListener
import me.dmdev.rxpm.action
import javax.inject.Inject

class AppPm @Inject constructor(
    services: ServiceFacade
) : BasePm(services), ConnectionListener {

    val coldStartAction = action<Unit>()

    override fun onCreate() {
        super.onCreate()

        coldStartAction.observable
            .doOnNext { router.newRootScreen(Screens.CurrencyListFlow) }
            .retry()
            .subscribe()
            .untilDestroy()
    }
}