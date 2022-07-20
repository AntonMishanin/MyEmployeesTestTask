package com.my.employee_details

import com.my.core.Converter
import com.my.employees_data.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeeDetailsConverter : Converter<Employee, EmployeeDetails> {

    override fun convert(input: Employee) = EmployeeDetails(
        id = input.id,
        avatarUrl = input.avatarUrl,
        name = input.firstName + " " + input.lastName,
        birthday = input.birthday,
        age = input.age,
        specialty = specialtiesToString(input.specialties),
        gender = "-"
    )

    private fun specialtiesToString(input: List<String>): String {
        val result = StringBuilder()

        for (i in input.indices) {
            val specialty = input[i]
            val value = when (i == input.size - 1) {
                true -> specialty
                false -> "${specialty}, "
            }
            result.append(value)
        }

        return result.toString()
    }
}