package com.my.employees.presentation

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */

interface EmployeeUi {

    val id: String

    fun match(data: EmployeeUi): Boolean

    data class Content(
        override val id: String,
        val name: String,
        val age: String,
        val avatarUrl: String
    ) : EmployeeUi {

        override fun match(data: EmployeeUi) = this == data
    }

    data class Progress(
        override val id: String = "Progress"
    ) : EmployeeUi {

        override fun match(data: EmployeeUi) = this == data
    }

    data class Error(
        override val id: String = "Error",
        val message: String
    ) : EmployeeUi {

        override fun match(data: EmployeeUi) = this == data
    }
}