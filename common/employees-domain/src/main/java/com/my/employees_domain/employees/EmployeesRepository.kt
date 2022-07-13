package com.my.employees_domain.employees

import kotlinx.coroutines.flow.Flow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
interface EmployeesRepository {

    suspend fun observeEmployees(): Flow<List<Employee>>

    suspend fun refreshEmployees()

    suspend fun fetchEmployee(id: String): Employee
}