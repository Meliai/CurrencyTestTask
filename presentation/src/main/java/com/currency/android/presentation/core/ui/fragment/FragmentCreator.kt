package com.currency.android.presentation.core.ui.fragment

import androidx.fragment.app.Fragment

/**
 * Interface to indicate classes which can provide [Fragment].
 */
interface FragmentCreator {
    fun create(tag: String): Fragment
}