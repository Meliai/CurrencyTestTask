package com.currency.android.presentation.core.pm.widgets

import android.net.NetworkInfo
import com.currency.android.presentation.core.pm.BasePm
import com.currency.android.presentation.core.pm.ReactiveNetworkFacade
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Class to observe network connectivity state changes.
 */
@Suppress("UnnecessaryParentheses", "UseDataClass")
class NetworkControl(
    network: ReactiveNetworkFacade,
    pm: BasePm
) {
    val observable: Observable<Boolean> = network.observeNetworkConnectivity()
        .map { it.state() == NetworkInfo.State.CONNECTED }
        .publish { u ->
            Observable.merge(u.take(1).filter { !it }, u.skip(1))
        }
        .distinctUntilChanged()
        .throttleFirst(1, TimeUnit.SECONDS)
        .doOnNext { connected ->
            pm.networkStateAction.consumer.accept(connected)
        }
}

fun BasePm.networkControl(network: ReactiveNetworkFacade) =
    NetworkControl(network, this)