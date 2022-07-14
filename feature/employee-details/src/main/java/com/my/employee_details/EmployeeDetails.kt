package com.my.employee_details

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
data class EmployeeDetails(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val age: String,
    val specialty: String
) {
    companion object {
        fun empty() = EmployeeDetails(
            id = "",
            firstName = "",
            lastName = "",
            birthday = "",
            age = "",
            specialty = ""
        )
    }
}