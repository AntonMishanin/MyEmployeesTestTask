package com.my.employees_domain.employees

import com.my.employees_domain.Specialty

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
data class Employee(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val avatarUrl: String,
    val specialties: List<Specialty>
)