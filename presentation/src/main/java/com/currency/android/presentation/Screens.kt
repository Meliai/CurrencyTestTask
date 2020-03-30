package com.currency.android.presentation

import androidx.fragment.app.Fragment
import com.currency.android.presentation.features.currency_list.ui.CurrencyListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object OnBoardingFlow : SupportAppScreen() {
        override fun getFragment(): Fragment = CurrencyListFragment.newInstance()
    }
}