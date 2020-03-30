package com.currency.android.presentation.core.pm.factory

import me.dmdev.rxpm.PresentationModel

/**
 * Base interface for all pm factories.
 */
interface PmFactory {
    fun <T : PresentationModel> createViewModel(modelClass: Class<T>): T
}