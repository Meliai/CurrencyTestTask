package com.currency.android.presentation.features.currency_list.ui

import android.os.Bundle
import android.view.View
import com.currency.android.presentation.R
import com.currency.android.presentation.core.ui.fragment.BaseListFragment
import com.currency.android.presentation.core.ui.system_ui.LightStatusBarConfigProvider
import com.currency.android.presentation.core.ui.system_ui.StatusBarConfigProvider
import com.currency.android.presentation.features.currency_list.pm.CurrencyListPm
import kotlinx.android.synthetic.main.layout_toolbar.*

class CurrencyListFragment : BaseListFragment<CurrencyListPm>() {

    override val screenLayout: Int = R.layout.fragment_currency_list
    override val classToken: Class<CurrencyListPm> = CurrencyListPm::class.java
    override val statusBarConfigProvider: StatusBarConfigProvider = LightStatusBarConfigProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleView.text = resources.getText(R.string.rates_screen_title)
    }

    companion object {
        fun newInstance(): CurrencyListFragment = CurrencyListFragment()
    }
}
