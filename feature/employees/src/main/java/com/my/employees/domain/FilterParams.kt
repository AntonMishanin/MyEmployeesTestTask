package com.my.employees.domain

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
data class FilterParams(
    val specialtiesId: Set<String> = HashSet()
)
