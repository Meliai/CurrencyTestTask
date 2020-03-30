package com.currency.android.data.features.feature1.datasource

import com.currency.android.data.features.feature1.dto.TestDto
import io.reactivex.Observable

interface TestDataSource {

    fun getTestModel(): Observable<TestDto>
}