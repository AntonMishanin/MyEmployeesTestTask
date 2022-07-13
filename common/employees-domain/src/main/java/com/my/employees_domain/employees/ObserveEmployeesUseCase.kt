package com.my.employees_domain.employees

import com.my.employees_domain.employees.Employee.Companion.copyWithCorrectInformation
import kotlinx.coroutines.flow.map

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class ObserveEmployeesUseCase(
    private val employeesRepository: EmployeesRepository
) {

    suspend fun invoke() = employeesRepository
        .observeEmployees()
        .map { it.copyWithCorrectInformation() }
}