package com.currency.android.presentation.core.navigation

import android.os.Handler
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command

/**
 * [Router] that executes all commands on the main thread.
 */
open class UiThreadRouter : Router() {

    private val handler = Handler()

    @Suppress("SpreadOperator")
    override fun executeCommands(vararg commands: Command?) {
        handler.post {
            super.executeCommands(*commands)
        }
    }
}