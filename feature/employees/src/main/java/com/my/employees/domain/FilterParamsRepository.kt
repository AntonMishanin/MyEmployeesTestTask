package com.my.employees.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
interface FilterParamsRepository {

    suspend fun observeFilterParams(): Flow<FilterParams>
}