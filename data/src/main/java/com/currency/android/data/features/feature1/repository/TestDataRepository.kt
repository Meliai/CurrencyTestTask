package com.currency.android.data.features.feature1.repository

import com.currency.common.mapper.Mapper
import com.currency.android.data.features.feature1.datasource.TestDataSource
import com.currency.android.data.features.feature1.dto.TestDto
import com.currency.android.domain.features.feature1.model.TestModel
import com.currency.android.domain.features.feature1.repository.TestRepository
import io.reactivex.Observable
import javax.inject.Inject

class TestDataRepository @Inject constructor(
    private val source: TestDataSource,
    private val mapper: Mapper<TestDto, TestModel>
) : TestRepository {

    override fun getTestModel(): Observable<TestModel> =
        source.getTestModel().map(mapper::mapFromObject)
}