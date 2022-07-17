package com.my.employees.presentation

import com.my.core.IterableConverter
import com.my.employees_data.Employee

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

    fun progress() = listOf(EmployeeUi.Progress())
}