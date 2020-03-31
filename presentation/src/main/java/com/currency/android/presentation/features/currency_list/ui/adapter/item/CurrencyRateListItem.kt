package com.currency.android.presentation.features.currency_list.ui.adapter.item

import com.nullgr.core.adapter.items.ListItem

data class CurrencyRateListItem(
    val currency: String,
    val rate: Double,
    val multiplier: Double,
    val isSelected: Boolean
) : ListItem {

    override fun getUniqueProperty(): Any = currency

    override fun getChangePayload(other: ListItem): Any {
        if (other is CurrencyRateListItem) {
            return mutableSetOf<Payload>().apply {
                if (rate != other.rate) add(Payload.RATE_CHANGED)
                if (multiplier != other.multiplier) add(Payload.MULTIPLIER_CHANGED)
                if (isSelected != other.isSelected) add(Payload.SELECTION_CHANGED)
            }
        }
        return super.getChangePayload(other)
    }

    enum class Payload {
        RATE_CHANGED, MULTIPLIER_CHANGED, SELECTION_CHANGED
    }
}