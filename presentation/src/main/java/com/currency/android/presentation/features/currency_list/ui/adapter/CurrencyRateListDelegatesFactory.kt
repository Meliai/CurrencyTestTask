package com.currency.android.presentation.features.currency_list.ui.adapter

import com.currency.android.presentation.features.currency_list.ui.adapter.delegate.CurrencyRateDelegate
import com.currency.android.presentation.features.currency_list.ui.adapter.item.CurrencyRateListItem
import com.nullgr.core.adapter.AdapterDelegate
import com.nullgr.core.adapter.AdapterDelegatesFactory
import com.nullgr.core.adapter.items.ListItem
import com.nullgr.core.resources.ResourceProvider
import com.nullgr.core.rx.RxBus
import javax.inject.Inject

class CurrencyRateListDelegatesFactory @Inject constructor(
    private val bus: RxBus,
    private val resources: ResourceProvider
) : AdapterDelegatesFactory {

    override fun createDelegate(clazz: Class<ListItem>): AdapterDelegate =
        when (clazz) {
            CurrencyRateListItem::class.java -> CurrencyRateDelegate(bus, resources)
            else -> throw IllegalArgumentException("No delegate defined for ${clazz.simpleName}")
        }
}