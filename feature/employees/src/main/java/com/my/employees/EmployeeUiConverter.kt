package com.my.employees

import com.my.core.IterableConverter
import com.my.employees_domain.employees.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeeUiConverter : IterableConverter<Employee, EmployeeUi>() {

    override fun convert(input: Employee) = EmployeeUi(
        id = input.id,
        firstName = input.firstName,
        lastName = input.lastName,
        age = input.age
    )
}