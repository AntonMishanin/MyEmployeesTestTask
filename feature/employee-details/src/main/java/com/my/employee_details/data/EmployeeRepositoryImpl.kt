package com.my.employee_details.data

import com.my.employee_details.domain.EmployeeRepository
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
import com.my.employees_data.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
class EmployeeRepositoryImpl(
    private val employeesStorage: EmployeesStorage,
    private val employeesConverter: EmployeesConverter
) : EmployeeRepository {

    override suspend fun fetchEmployee(id: String): Employee {
        val employeeDbo = employeesStorage.read(id)
        return employeesConverter.convert(employeeDbo)
    }
}