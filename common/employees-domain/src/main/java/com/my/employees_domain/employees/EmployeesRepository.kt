package com.my.employees_domain.employees

import com.my.employees_domain.EmployeeResult
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
interface EmployeesRepository {

    suspend fun observeFilterParams(): Flow<EmployeeResult>

    suspend fun refreshEmployees()

    suspend fun fetchEmployee(id: String): Employee
}