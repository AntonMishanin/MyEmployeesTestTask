package com.my.employees.domain

import com.my.employees_data.Employee
import com.my.employees_data.Employee.Companion.copyWithCorrectInformation
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class ObserveEmployeesUseCase(
    private val employeesRepository: EmployeesRepository,
    private val filterParamsRepository: FilterParamsRepository
) {

    suspend fun invoke() = employeesRepository.observeEmployees()
        .combine(filterParamsRepository.observeFilterParams())
        { employees, filterParams ->
            if (filterParams.specialtiesId.isEmpty()) {
                employees
            } else {
                matchEmployeeWithFilterParams(filterParams, employees)
            }
        }
        .map { it.copyWithCorrectInformation() }

    private fun matchEmployeeWithFilterParams(
        filterParams: FilterParams,
        employees: List<Employee>
    ) = employees.filter { employee ->
        var matches = false

        employee.specialties.forEach {
            if (filterParams.specialtiesId.contains(it)) {
                matches = true
            }
        }
        matches
    }
}