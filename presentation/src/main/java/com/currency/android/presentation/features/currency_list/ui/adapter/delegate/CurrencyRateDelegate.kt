package com.currency.android.presentation.features.currency_list.ui.adapter.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.currency.android.presentation.Events
import com.currency.android.presentation.R
import com.currency.android.presentation.core.bus.event
import com.currency.android.presentation.features.currency_list.chooseDrawable
import com.currency.android.presentation.features.currency_list.ui.adapter.item.CurrencyRateListItem
import com.currency.android.presentation.utils.loadImage
import com.currency.android.presentation.utils.withAdapterPosition
import com.google.android.material.textfield.TextInputEditText
import com.nullgr.core.adapter.items.ListItem
import com.nullgr.core.adapter.ktx.AdapterDelegate
import com.nullgr.core.adapter.ktx.ViewHolder
import com.nullgr.core.resources.ResourceProvider
import com.nullgr.core.rx.RxBus
import com.nullgr.core.ui.extensions.addTextChangedListener
import kotlinx.android.synthetic.main.item_currency_rate.*
import kotlinx.android.synthetic.main.item_currency_rate.view.*
import java.text.DecimalFormat
import java.util.Currency

class CurrencyRateDelegate(
    private val bus: RxBus,
    private val resource: ResourceProvider
) : AdapterDelegate() {
    override val layoutResource: Int = R.layout.item_currency_rate
    override val itemType: Any = CurrencyRateListItem::class

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent).apply {
            with(this as ViewHolder) {
                //                itemView.setOnClickListener {
//                    withAdapterPosition<CurrencyRateListItem> { _, _, _ ->
//                        bus.
//                    }
//                }
                itemView.currencyRateText.addTextChangedListener(
                    onTextChanged = { text, _, _, _ ->
                        withAdapterPosition<CurrencyRateListItem> { _, item, _ ->
                            if (item.isSelected) {
                                val amount: Double = if (text.toString().isNotEmpty()) {
                                    text.toString().toDouble()
                                } else 0.0
                                bus.event(Events.OnMultiplierChanged(amount))
                            }

                        }
                    }
                )
            }
        }
    }

    override fun onBindViewHolder(items: List<ListItem>, position: Int, holder: RecyclerView.ViewHolder) {
        val item = items[position] as CurrencyRateListItem

        with(holder as ViewHolder) {
            currencyImageView.loadImage(
                item.currency.chooseDrawable(),
                R.drawable.ic_no_photo_gray_small,
                R.drawable.ic_no_photo_gray_small
            )

            currencyCodeTextView.text = item.currency
            currencyNameTextView.text = Currency.getInstance(item.currency).displayName.capitalize()
            currencyRateText.updateRateTextView(item.rate, item.multiplier, item.isSelected)
        }
    }

    override fun onBindViewHolder(items: List<ListItem>, position: Int, holder: RecyclerView.ViewHolder, payload: Any) {
        val item = items[position] as CurrencyRateListItem
        with(holder as ViewHolder) {
            when (payload) {
                CurrencyRateListItem.Payload.RATE_CHANGED,
                CurrencyRateListItem.Payload.MULTIPLIER_CHANGED ->
                    currencyRateText.updateRateTextView(item.rate, item.multiplier, item.isSelected)
                CurrencyRateListItem.Payload.SELECTION_CHANGED -> {
                }
            }
        }
    }

    private fun convertRate(rate: Double, multiplier: Double) =
        if (multiplier > 0) DecimalFormat(RATE_FORMAT).format(rate * multiplier) else ""

    private fun TextInputEditText.updateRateTextView(rate: Double, multiplier: Double, isSelected: Boolean) {
        setText(convertRate(rate, multiplier))
        if (isSelected) setSelection(this.text?.length ?: 0)
    }

    private companion object {
        const val RATE_FORMAT = "##.##"
    }
}