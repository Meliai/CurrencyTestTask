package com.currency.android.presentation.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.currency.android.presentation.core.navigation.commands.AddTabs
import com.currency.android.presentation.core.navigation.commands.AttachTab
import com.currency.android.presentation.core.navigation.commands.ShowDialog
import com.nullgr.core.ui.fragments.showDialog
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

/**
 * [SupportAppNavigator] with support for additional commands: [AttachTab], [AddTabs] [ShowDialog].
 */
open class ExtendedNavigator(
    activity: FragmentActivity?,
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    private val tabsHolder = mutableMapOf<String, Fragment>()

    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        fragmentTransaction.apply {
            setReorderingAllowed(true)
        }
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is AttachTab -> attachTabFragment(command.screen)
            is AddTabs -> replaceTabFragments(command.screens)
            is ShowDialog -> showDialogFragment(command.screen)
            else -> super.applyCommand(command)
        }
    }

    private fun showDialogFragment(screen: DialogScreen) {
        fragmentManager.showDialog(screen.fragment)
    }

    private fun attachTabFragment(screen: SupportAppScreen) {
        checkScreenExistence(screen)
        fragmentManager.beginTransaction().apply {
            tabsHolder.forEach {
                when (screen.screenKey) {
                    it.key -> attach(it.value)
                    else -> detach(it.value)
                }
            }
        }.commitNow()
    }

    private fun replaceTabFragments(screens: Array<SupportAppScreen>) {
        tabsHolder.clear()
        screens.forEach { addTabFragment(it) }
    }

    private fun addTabFragment(screen: SupportAppScreen) {
        tabsHolder[screen.screenKey] = fragmentManager.initializeSingleTab(
            screen.fragment,
            containerId,
            screen.screenKey
        )
    }

    private fun FragmentManager.initializeSingleTab(
        fragment: Fragment,
        containerId: Int,
        tag: String
    ): Fragment =
        findFragmentByTag(tag) ?: fragment.apply {
            beginTransaction()
                .add(containerId, this, tag)
                .detach(this)
                .commitNow()
        }

    private fun checkScreenExistence(screen: SupportAppScreen) {
        if (tabsHolder.isEmpty() && !fragmentManager.fragments.isNullOrEmpty()) {
            fragmentManager.fragments.firstOrNull()?.let { fragment ->
                fragment.tag?.let {
                    tabsHolder[it] = fragment
                }
            }
        }
        if (!tabsHolder.containsKey(screen.screenKey)) {
            addTabFragment(screen)
        }
    }
}