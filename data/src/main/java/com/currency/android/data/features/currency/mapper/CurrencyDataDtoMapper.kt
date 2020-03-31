package com.currency.android.data.features.currency.mapper

import com.currency.android.data.features.currency.dto.CurrencyDataDto
import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.common.mapper.Mapper
import javax.inject.Inject

class CurrencyDataDtoMapper @Inject constructor() : Mapper<CurrencyDataDto, @JvmSuppressWildcards List<CurrencyModel>> {
    override fun mapFromObject(source: CurrencyDataDto): List<CurrencyModel> =
        with(source) {
            rates.map { (currency, rate) ->
                CurrencyModel(
                    currency = currency,
                    rate = rate,
                    isDefault = currency == defaultCurrency
                )
            }
        }
}