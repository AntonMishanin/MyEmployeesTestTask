package com.my.core

/**
 * @Author: Anton Mishanin
 * @Date: 7/20/2022
 */
sealed interface NavigationDestination {

    object Back : NavigationDestination

    data class EmployeeDetails(
        val id: String
    ) : NavigationDestination

    object EmployeesRoot : NavigationDestination
}