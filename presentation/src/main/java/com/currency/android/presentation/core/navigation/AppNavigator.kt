package com.currency.android.presentation.core.navigation

import androidx.fragment.app.FragmentActivity
import com.currency.android.presentation.R

/**
 * [ExtendedNavigator] for [FragmentActivity].
 */
class AppNavigator(
    activity: FragmentActivity
) : ExtendedNavigator(activity, activity.supportFragmentManager, R.id.containerView)