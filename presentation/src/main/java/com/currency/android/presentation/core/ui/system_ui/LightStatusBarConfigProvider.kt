package com.currency.android.presentation.core.ui.system_ui

import com.currency.android.presentation.R

/**
 * Configuration set for light status bar.
 */
object LightStatusBarConfigProvider : StatusBarConfigProvider {
    override var applyStatusBarInsetToContentView = true
    override val statusBarColor: Int = R.color.color_status_bar_light
    override val lightStatusBar: Boolean = false
}