package com.my.employee_details.domain

import com.my.employees_data.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
interface EmployeeRepository {

    suspend fun fetchEmployee(id: String): Employee
}