package com.currency.android.presentation.core.pm

import com.nullgr.core.resources.ResourceProvider
import com.nullgr.core.rx.RxBus
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class which combines different services like [ResourceProvider], [ReactiveNetworkFacade], [Analytics], [RxBus]
 * and [ExceptionParser].
 */
@Suppress("UseDataClass")
@Singleton
class ServiceFacade @Inject constructor(
    resourceProvider: ResourceProvider,
    networkFacade: ReactiveNetworkFacade,
    rxBus: RxBus
) {
    val resources: ResourceProvider = resourceProvider
    val network: ReactiveNetworkFacade = networkFacade
    val bus: RxBus = rxBus
}