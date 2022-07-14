package com.my.employees_domain.employees

import com.my.employees_domain.FilterParams
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
interface EmployeesRepository {

    suspend fun observeFilterParams(): Flow<Pair<FilterParams, List<Employee>>>

    suspend fun refreshEmployees()

    suspend fun fetchEmployee(id: String): Employee
}