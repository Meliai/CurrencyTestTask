package com.currency.android.data.features.currency.mapper

import com.currency.common.mapper.Mapper
import com.currency.android.data.features.currency.dto.TestDto
import com.currency.android.domain.features.feature1.model.TestModel
import javax.inject.Inject

class TestDtoMapper @Inject constructor() : Mapper<TestDto, TestModel> {
    override fun mapFromObject(source: TestDto): TestModel =
        TestModel(source.id)
}