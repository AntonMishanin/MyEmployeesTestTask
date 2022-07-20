package com.my.employees

import com.my.core.IterableConverter
import com.my.employees_domain.EmployeeResult
import com.my.employees_domain.employees.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeeUiConverter : IterableConverter<Employee, EmployeeUi>() {

    override fun convert(input: Employee) = EmployeeUi.Content(
        id = input.id,
        firstName = input.firstName,
        lastName = input.lastName,
        age = input.age
    )

    fun convert(result: EmployeeResult): List<EmployeeUi> {
        return if (result.exception == null) convert(result.employees)
        else listOf(EmployeeUi.Error(message = ""))// TODO: think about different error types
    }

    fun progress() = listOf(EmployeeUi.Progress())
}