package com.currency.android.presentation.core.navigation

import com.currency.android.presentation.core.navigation.commands.AddTabs
import com.currency.android.presentation.core.navigation.commands.AttachTab
import com.currency.android.presentation.core.navigation.commands.ShowDialog
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Special [UiThreadRouter] for screens with complicated flow.
 */
class FlowRouter(private val parentRouter: Router?) : UiThreadRouter() {

    fun startFlow(screen: SupportAppScreen) {
        runCommand { navigateTo(screen) }
    }

    fun newRootFlow(screen: SupportAppScreen) {
        runCommand { newRootScreen(screen) }
    }

    fun finishFlow() {
        runCommand { exit() }
    }

    fun newTabs(screens: Array<SupportAppScreen>) {
        executeCommands(AddTabs(screens))
    }

    fun navigateToTab(screen: SupportAppScreen) {
        executeCommands(AttachTab(screen))
    }

    fun showDialog(screen: DialogScreen) {
        executeCommands(ShowDialog(screen))
    }

    private fun runCommand(command: Router.() -> Unit) {
        if (parentRouter != null)
            parentRouter.command()
        else
            this.command()
    }
}
