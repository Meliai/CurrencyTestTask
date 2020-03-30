package com.currency.android.presentation.core.navigation.commands

import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

/**
 * [Command] for adding tab screens.
 */
class AddTabs(val screens: Array<SupportAppScreen>) : Command