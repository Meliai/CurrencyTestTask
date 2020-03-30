package com.currency.android.presentation.features.currency_list.ui

import com.currency.android.presentation.R
import com.currency.android.presentation.core.ui.fragment.BaseFragment
import com.currency.android.presentation.core.ui.system_ui.LightStatusBarConfigProvider
import com.currency.android.presentation.core.ui.system_ui.StatusBarConfigProvider
import com.currency.android.presentation.features.currency_list.pm.CurrencyListPm

class CurrencyListFragment : BaseFragment<CurrencyListPm>() {

    override val screenLayout: Int = R.layout.fragment_currency_list
    override val classToken: Class<CurrencyListPm> = CurrencyListPm::class.java
    override val statusBarConfigProvider: StatusBarConfigProvider = LightStatusBarConfigProvider

    companion object {
        fun newInstance(): CurrencyListFragment = CurrencyListFragment()
    }
}
