package com.currency.android.presentation.features.currency_list.builder

import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.android.presentation.features.currency_list.ui.adapter.item.CurrencyRateListItem
import com.currency.common.mapper.Mapper
import com.nullgr.core.adapter.items.ListItem
import javax.inject.Inject

class CurrencyRateListBuilder @Inject constructor() : Mapper<CurrencyModel, ListItem> {

    override fun mapFromObject(source: CurrencyModel): ListItem =
        with(source) {
            CurrencyRateListItem(
                currency = currency,
                rate = rate,
                isSelected = isDefault,
                multiplier = multiplier
            )
        }
}