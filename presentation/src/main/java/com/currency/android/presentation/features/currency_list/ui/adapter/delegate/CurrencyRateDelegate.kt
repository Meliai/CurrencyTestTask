package com.currency.android.presentation.features.currency_list.ui.adapter.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.currency.android.presentation.R
import com.currency.android.presentation.features.currency_list.ui.adapter.item.CurrencyRateListItem
import com.currency.android.presentation.utils.loadImage
import com.currency.android.presentation.utils.withAdapterPosition
import com.nullgr.core.adapter.items.ListItem
import com.nullgr.core.adapter.ktx.AdapterDelegate
import com.nullgr.core.adapter.ktx.ViewHolder
import com.nullgr.core.resources.ResourceProvider
import com.nullgr.core.rx.RxBus
import kotlinx.android.synthetic.main.item_currency_rate.*
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
                itemView.setOnClickListener {
                    withAdapterPosition<CurrencyRateListItem> { _, _, _ ->
                        //                        bus.click(Clicks.ProductCardClicked(item.articul))
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(items: List<ListItem>, position: Int, holder: RecyclerView.ViewHolder) {
        val item = items[position] as CurrencyRateListItem

        with(holder as ViewHolder) {
            currencyImageView.loadImage(
                R.drawable.ic_cad_flag,
                R.drawable.ic_no_photo_gray_small,
                R.drawable.ic_no_photo_gray_small
            )

            currencyCodeTextView.text = item.currency
            currencyNameTextView.text = Currency.getInstance(item.currency).displayName
            currencyRateText.setText(convertRate(item.rate, item.multiplier))
        }
    }

    override fun onBindViewHolder(items: List<ListItem>, position: Int, holder: RecyclerView.ViewHolder, payload: Any) {
        val item = items[position] as CurrencyRateListItem
        with(holder as ViewHolder) {
            when (payload) {
                CurrencyRateListItem.Payload.RATE_CHANGED ->
                    currencyRateText.setText(convertRate(item.rate, item.multiplier))
                CurrencyRateListItem.Payload.MULTIPLIER_CHANGED ->
                    currencyRateText.setText(convertRate(item.rate, item.multiplier))
                CurrencyRateListItem.Payload.SELECTION_CHANGED -> {
                }
            }
        }
    }

    private fun convertRate(rate: Double, multiplier: Double): String =
        (rate * multiplier).toString()
}