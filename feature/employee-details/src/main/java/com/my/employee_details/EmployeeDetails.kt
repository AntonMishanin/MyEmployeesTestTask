package com.my.employee_details

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
data class EmployeeDetails(
    val id: String,
    val avatarUrl: String,
    val name: String,
    val birthday: String,
    val age: String,
    val specialty: String,
    val gender: String
) {
    companion object {
        fun empty() = EmployeeDetails(
            id = "",
            avatarUrl = "",
            name = "",
            birthday = "",
            age = "",
            specialty = "",
            gender = ""
        )
    }
}