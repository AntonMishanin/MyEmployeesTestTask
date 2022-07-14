package com.my.employees_domain.employees

import com.my.employees_domain.FilterParams
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
        .observeFilterParams()
        .map { result ->
            if (result.filterParams.specialtiesId.isEmpty()) {
                result
            } else {
                val employees = matchEmployeeWithFilterParams(result.filterParams, result.employees)
                result.copy(employees = employees)
            }
        }
        .map {
            val employee = it.employees.copyWithCorrectInformation()
            it.copy(employees = employee)
        }

    private fun matchEmployeeWithFilterParams(
        filterParams: FilterParams,
        employees: List<Employee>
    ) = employees.filter { employee ->
        var matches = false

        employee.specialties.forEach {
            if (filterParams.specialtiesId.contains(it.id.toString())) {
                matches = true
            }
        }
        matches
    }
}