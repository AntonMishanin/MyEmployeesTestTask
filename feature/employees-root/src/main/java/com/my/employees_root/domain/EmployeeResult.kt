package com.my.employees_root

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */

sealed interface EmployeesResult {

    object Content : EmployeesResult

    object Progress : EmployeesResult

    data class ContentWithError(
        val exception: Exception
    ) : EmployeesResult

    data class EmptyContentError(
        val exception: Exception
    ) : EmployeesResult
}