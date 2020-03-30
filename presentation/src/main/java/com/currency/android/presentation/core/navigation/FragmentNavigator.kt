package com.currency.android.presentation.core.navigation

import androidx.fragment.app.Fragment
import com.currency.android.presentation.R

/**
 * [ExtendedNavigator] for [Fragment].
 */
open class FragmentNavigator(
    fragment: Fragment
) : ExtendedNavigator(fragment.activity, fragment.childFragmentManager, R.id.containerView)