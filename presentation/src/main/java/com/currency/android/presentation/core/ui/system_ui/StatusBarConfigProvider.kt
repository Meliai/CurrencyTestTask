package com.currency.android.presentation.core.ui.system_ui

/**
 * Interface that provides data for status bar configuration.
 */
interface StatusBarConfigProvider {

    val statusBarColor: Int
    val lightStatusBar: Boolean
    var applyStatusBarInsetToContentView: Boolean
}