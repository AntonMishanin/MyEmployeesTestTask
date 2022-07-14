package com.my.employees_domain

import com.my.employees_domain.employees.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
data class EmployeeResult(
    val employees: List<Employee> = emptyList(),
    val filterParams: FilterParams = FilterParams(),
    val exception: Exception? = null
)