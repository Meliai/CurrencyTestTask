package com.currency.android.presentation.core.pm

import androidx.annotation.CallSuper
import me.dmdev.rxpm.action

/**
 * Base class for flow pms.
 */
abstract class BaseFlowPm(
    services: ServiceFacade
) : BasePm(services) {

    val launchScreenAction = action<Unit>()

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        launchScreenAction
            .observable
            .doOnNext { navigateToLaunchScreen() }
            .subscribe()
            .untilDestroy()
    }

    abstract fun navigateToLaunchScreen()
}