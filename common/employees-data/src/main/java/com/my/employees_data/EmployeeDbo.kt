package com.my.employees_data

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
data class EmployeeDbo(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val avatarUrl: String,
    val specialties: String
)