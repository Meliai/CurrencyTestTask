package com.currency.android.data.features.currency.repository

import com.currency.android.data.features.currency.datasource.CurrencyDataSource
import com.currency.android.data.features.currency.dto.CurrencyDataDto
import com.currency.android.domain.features.currency.model.CurrencyModel
import com.currency.android.domain.features.currency.repository.CurrencyRepository
import com.currency.common.mapper.Mapper
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyDataRepository @Inject constructor(
    private val source: CurrencyDataSource,
    private val mapper: Mapper<CurrencyDataDto, List<CurrencyModel>>
) : CurrencyRepository {

    override fun getLatestCurrencyData(baseCurrency: String): Observable<List<CurrencyModel>> =
        source.getCurrencyData(baseCurrency).map {
            it.rates[baseCurrency] = 10.0
            mapper.mapFromObject(it)
        }
}