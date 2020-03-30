package com.currency.android.presentation.core.navigation.commands

import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

/**
 * [Command] for attaching screen.
 */
class AttachTab(val screen: SupportAppScreen) : Command