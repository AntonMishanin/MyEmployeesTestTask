package com.my.employees.domain

import com.my.employees_data.Employee
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
interface EmployeesRepository {

    suspend fun observeEmployees(): Flow<List<Employee>>

    suspend fun fetchEmployees(): List<Employee>

    suspend fun fetchEmployee(id: String): Employee
}