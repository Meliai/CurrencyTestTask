package com.currency.android.presentation.features.onboaring.ui

import android.os.Bundle
import com.currency.android.presentation.R
import com.currency.android.presentation.features.onboaring.pm.OnBoardingPm
import com.currency.android.presentation.core.ui.fragment.BaseFragment
import com.currency.android.presentation.core.ui.system_ui.LightStatusBarConfigProvider
import com.currency.android.presentation.core.ui.system_ui.StatusBarConfigProvider

class OnBoardingFragment : BaseFragment<OnBoardingPm>() {

    override val screenLayout: Int = R.layout.fragment_onboarding
    override val classToken: Class<OnBoardingPm> = OnBoardingPm::class.java
    override val statusBarConfigProvider: StatusBarConfigProvider = LightStatusBarConfigProvider

    companion object {
        fun newInstance(): OnBoardingFragment {
            return OnBoardingFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
}
