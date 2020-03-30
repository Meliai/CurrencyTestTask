package com.currency.android.data.features.currency.repository

import com.currency.common.mapper.Mapper
import com.currency.android.data.features.currency.datasource.CurrencyDataSource
import com.currency.android.data.features.currency.dto.CurrencyDataDto
import com.currency.android.domain.features.feature1.model.TestModel
import com.currency.android.domain.features.feature1.repository.TestRepository
import io.reactivex.Observable
import javax.inject.Inject

class TestDataRepository @Inject constructor(
    private val source: CurrencyDataSource,
    private val mapper: Mapper<CurrencyDataDto, TestModel>
) : TestRepository {

    override fun getTestModel(): Observable<TestModel> = Observable.just(TestModel("test"))
}