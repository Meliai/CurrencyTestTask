@file:Suppress("UNCHECKED_CAST")

package com.currency.android.presentation.core.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

/**
 * Class that holds [Cicerone] instances.
 */
class LocalCiceroneHolder {

    private val containers = hashMapOf<String, Cicerone<Router>>()

    operator fun get(containerTag: String): Cicerone<Router> =
        containers[containerTag] ?: Cicerone.create(UiThreadRouter()).apply {
            containers[containerTag] = this as Cicerone<Router>
        } as Cicerone<Router>
}