package com.my.employees_domain

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
data class FilterParams(
    val specialtiesId: HashSet<String> = HashSet()
)
