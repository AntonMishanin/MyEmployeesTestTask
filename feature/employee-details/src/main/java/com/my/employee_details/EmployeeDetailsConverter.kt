package com.my.employee_details

import com.my.core.Converter
import com.my.employees_domain.Specialty
import com.my.employees_domain.employees.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeeDetailsConverter : Converter<Employee, EmployeeDetails> {

    override fun convert(input: Employee) = EmployeeDetails(
        id = input.id,
        firstName = input.firstName,
        lastName = input.lastName,
        birthday = input.birthday,
        age = input.age,
        specialty = specialtiesToString(input.specialties)
    )

    private fun specialtiesToString(input: List<Specialty>): String {
        val result = StringBuilder()

        for (i in input.indices) {
            val specialty = input[i].name
            val value = when (i == input.size - 1) {
                true -> specialty
                false -> "${specialty}, "
            }
            result.append(value)
        }

        return result.toString()
    }
}